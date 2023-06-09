/*
 * Copyright 2013-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.awspring.cloud.core.config;

import java.net.URI;

import com.amazonaws.AmazonWebServiceClient;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.regions.Region;
import io.awspring.cloud.core.SpringCloudClientConfiguration;

import org.springframework.test.util.ReflectionTestUtils;

import static org.springframework.util.Assert.notNull;

/**
 * Test stub used by {@link AmazonWebserviceClientConfigurationUtilsTest}
 *
 * @author Agim Emruli
 * @author Eddú Meléndez
 */
class AmazonTestWebserviceClient extends AmazonWebServiceClient {

	private Region region;

	AmazonTestWebserviceClient(AWSCredentialsProvider awsCredentialsProvider) {
		super(SpringCloudClientConfiguration.getClientConfiguration());
		notNull(awsCredentialsProvider, "CredentialsProvider must not be null");
	}

	Region getRegion() {
		return this.region;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void setRegion(Region region) {
		this.region = region;
	}

	public String getSigningRegion() {
		return super.getSigningRegion();
	}

	public URI getEndpoint() {
		return (URI) ReflectionTestUtils.getField(this, "endpoint");
	}

	public boolean isEndpointOverridden() {
		return isEndpointOverridden;
	}

}
