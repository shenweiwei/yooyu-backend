package com.yooyu.backend.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import software.amazon.awssdk.core.auth.AwsCredentials;
import software.amazon.awssdk.core.auth.AwsCredentialsProvider;
import software.amazon.awssdk.core.auth.StaticCredentialsProvider;
import software.amazon.awssdk.core.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Component
public class S3ClientFactory {
	@Value("${aws.account.accessKeyId}")
	private String accessKeyId;
	@Value("${aws.account.secretAccessKey}")
	private String secretAccessKey;
	@Value("${aws.bucket.region}")
	private String region;

	private static S3Client client;

	
	public S3Client getInstance() {
		if (client == null) {
			AwsCredentials awsCredentials = AwsCredentials.create(accessKeyId, secretAccessKey);
			AwsCredentialsProvider awsCredentialsProvider = StaticCredentialsProvider.create(awsCredentials);
			client = S3Client.builder()
					.region(Region.of(region))
					.credentialsProvider(awsCredentialsProvider).build();
		}

		return client;
	}

}
