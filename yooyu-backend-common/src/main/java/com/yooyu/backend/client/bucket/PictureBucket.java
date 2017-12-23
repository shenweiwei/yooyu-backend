package com.yooyu.backend.client.bucket;

import java.nio.ByteBuffer;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.yooyu.backend.client.S3ClientFactory;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.core.sync.StreamingResponseHandler;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.CompleteMultipartUploadRequest;
import software.amazon.awssdk.services.s3.model.CompleteMultipartUploadResponse;
import software.amazon.awssdk.services.s3.model.CompletedMultipartUpload;
import software.amazon.awssdk.services.s3.model.CompletedPart;
import software.amazon.awssdk.services.s3.model.CreateMultipartUploadRequest;
import software.amazon.awssdk.services.s3.model.CreateMultipartUploadResponse;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.DeleteObjectResponse;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;
import software.amazon.awssdk.services.s3.model.UploadPartRequest;

@Component
@PropertySource("classpath:/application.properties")
public class PictureBucket extends BucketAbstract {
	@Autowired
	private S3ClientFactory clientFactory;

	@Value("${aws.bucket.pic}")
	private String picBucketName;

	@Override
	public PutObjectResponse putObject(String filename, RequestBody requestBody) {
		PutObjectRequest request = PutObjectRequest.builder().bucket(picBucketName).key(filename).build();
		PutObjectResponse response = getS3Client().putObject(request, requestBody);
		return response;
	}

	@Override
	public Object getObject(String filename, RequestBody requestBody) {
		GetObjectRequest request = GetObjectRequest.builder().bucket(picBucketName).key(filename).build();
		Object response = getS3Client().getObject(request, StreamingResponseHandler.toBytes());
		return response;
	}

	@Override
	public CompleteMultipartUploadResponse multipartPutObject(String filename, RequestBody requestBody) {
		int MB = 1024 * 1024;
		// First create a multipart upload and get upload id
		CreateMultipartUploadRequest createMultipartUploadRequest = CreateMultipartUploadRequest.builder()
				.bucket(picBucketName).key(filename).build();
		CreateMultipartUploadResponse response = this.getS3Client().createMultipartUpload(createMultipartUploadRequest);
		String uploadId = response.uploadId();
		System.out.println(uploadId);

		// Upload all the different parts of the object
		UploadPartRequest uploadPartRequest1 = UploadPartRequest.builder().bucket(picBucketName).key(filename)
				.uploadId(uploadId).partNumber(1).build();
		String etag1 = this.getS3Client().uploadPart(uploadPartRequest1, RequestBody.of(getRandomByteBuffer(5 * MB)))
				.eTag();
		CompletedPart part1 = CompletedPart.builder().partNumber(1).eTag(etag1).build();

		UploadPartRequest uploadPartRequest2 = UploadPartRequest.builder().bucket(picBucketName).key(filename)
				.uploadId(uploadId).partNumber(2).build();
		String etag2 = this.getS3Client().uploadPart(uploadPartRequest2, RequestBody.of(getRandomByteBuffer(3 * MB)))
				.eTag();
		CompletedPart part2 = CompletedPart.builder().partNumber(2).eTag(etag2).build();

		// Finally call completeMultipartUpload operation to tell S3 to merge
		// all uploaded
		// parts and finish the multipart operation.
		CompletedMultipartUpload completedMultipartUpload = CompletedMultipartUpload.builder().parts(part1, part2)
				.build();
		CompleteMultipartUploadRequest completeMultipartUploadRequest = CompleteMultipartUploadRequest.builder()
				.bucket(picBucketName).key(filename).uploadId(uploadId).multipartUpload(completedMultipartUpload)
				.build();
		CompleteMultipartUploadResponse finalresponse = this.getS3Client()
				.completeMultipartUpload(completeMultipartUploadRequest);

		return finalresponse;
	}

	@Override
	public DeleteObjectResponse deleteObject(String filename) {
		DeleteObjectRequest request = DeleteObjectRequest.builder().bucket(picBucketName).key(filename).build();
		DeleteObjectResponse response = getS3Client().deleteObject(request);
		return response;
	}

	private S3Client getS3Client() {
		return clientFactory.getInstance();
	}

	private ByteBuffer getRandomByteBuffer(int size) {
		try {
			byte[] b = new byte[size];
			new Random().nextBytes(b);
			return ByteBuffer.wrap(b);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
