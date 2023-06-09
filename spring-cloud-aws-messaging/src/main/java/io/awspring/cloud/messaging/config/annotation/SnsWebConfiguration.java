/*
 * Copyright 2013-2019 the original author or authors.
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

package io.awspring.cloud.messaging.config.annotation;

import java.util.List;
import java.util.Optional;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.message.SnsMessageManager;
import io.awspring.cloud.context.annotation.ConditionalOnClass;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static io.awspring.cloud.messaging.endpoint.config.NotificationHandlerMethodArgumentResolverConfigurationUtils.getNotificationHandlerMethodArgumentResolver;

/**
 * @author Agim Emruli
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnClass("org.springframework.web.servlet.config.annotation.WebMvcConfigurer")
@Deprecated
public class SnsWebConfiguration {

	@Bean
	public WebMvcConfigurer snsWebMvcConfigurer(AmazonSNS amazonSns, Optional<SnsMessageManager> snsMessageManager) {
		return new WebMvcConfigurer() {
			@Override
			public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
				argumentResolvers.add(getNotificationHandlerMethodArgumentResolver(amazonSns, snsMessageManager));
			}
		};
	}

}
