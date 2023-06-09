/*
 * Copyright 2013-2020 the original author or authors.
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

package io.awspring.cloud.autoconfigure.messaging;

import io.awspring.cloud.core.config.AwsClientProperties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration properties for AWS Simple Notification Service.
 *
 * @author Eddú Meléndez
 */
@ConfigurationProperties(prefix = "cloud.aws.sns")
public class SnsProperties extends AwsClientProperties {

	/**
	 * Defines if SNS massages will be verified. By default, verification is used.
	 */
	private boolean verification = true;

	public boolean getVerification() {
		return verification;
	}

	public void setVerification(boolean verification) {
		this.verification = verification;
	}

}
