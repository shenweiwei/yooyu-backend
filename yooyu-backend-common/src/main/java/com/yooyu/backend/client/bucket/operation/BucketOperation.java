package com.yooyu.backend.client.bucket.operation;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.model.CompleteMultipartUploadResponse;
import software.amazon.awssdk.services.s3.model.DeleteObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

public interface BucketOperation {

	public PutObjectResponse putObject(String filename,RequestBody requestBody);
	
	public Object getObject(String filename);
	
	public CompleteMultipartUploadResponse multipartPutObject(String filename,RequestBody requestBody);
	
	public DeleteObjectResponse deleteObject(String filename);
}
