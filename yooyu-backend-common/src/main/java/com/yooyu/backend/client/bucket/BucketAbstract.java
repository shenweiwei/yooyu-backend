package com.yooyu.backend.client.bucket;

import com.yooyu.backend.client.bucket.operation.BucketOperation;

public abstract class BucketAbstract implements BucketOperation{
	private String bucketName;
	
	public String getBucketName() {
		return bucketName;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}
}
