package com.workshop.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

//CLASSE DE CONFIGURAÇÃO DO S3/BUCKET
@Configuration
public class S3Config {

	@Value("${aws.access_key_id}")
	private String awsId;

	@Value("${aws.secret_access_key}") ///awsId,awsKey,awsRegion CORRESPONDE AOS VALORES PASSADOS NO APPLICATION.PROPERTIES
	private String awsKey;

	@Value("${s3.region}")
	private String awsRegion;

	@Bean
	public AmazonS3 s3client() {
		BasicAWSCredentials awsCred = new BasicAWSCredentials(awsId, awsKey);
		AmazonS3 s3client = AmazonS3ClientBuilder.standard().withRegion(Regions.fromName(awsRegion)) ///IRÁ PERIMITIR A CHECAGEM DAS CREDENCIAIS DO S3/BUCKET
				.withCredentials(new AWSStaticCredentialsProvider(awsCred)).build();

		return s3client;

	}

}
