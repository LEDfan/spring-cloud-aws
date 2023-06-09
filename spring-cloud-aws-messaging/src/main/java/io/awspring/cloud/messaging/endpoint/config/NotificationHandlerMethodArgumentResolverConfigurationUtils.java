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

package io.awspring.cloud.messaging.endpoint.config;

import java.util.Optional;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.message.SnsMessageManager;
import io.awspring.cloud.messaging.endpoint.NotificationMessageHandlerMethodArgumentResolver;
import io.awspring.cloud.messaging.endpoint.NotificationStatusHandlerMethodArgumentResolver;
import io.awspring.cloud.messaging.endpoint.NotificationSubjectHandlerMethodArgumentResolver;

import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolverComposite;

/**
 * @author Alain Sahli
 * @since 1.0
 */
public final class NotificationHandlerMethodArgumentResolverConfigurationUtils {

	private NotificationHandlerMethodArgumentResolverConfigurationUtils() {
		throw new IllegalStateException("Can't instantiate a utility class");
	}

	public static HandlerMethodArgumentResolver getNotificationHandlerMethodArgumentResolver(AmazonSNS amazonSns,
			Optional<SnsMessageManager> snsMessageManager) {
		HandlerMethodArgumentResolverComposite composite = new HandlerMethodArgumentResolverComposite();
		composite.addResolver(new NotificationStatusHandlerMethodArgumentResolver(amazonSns));
		if (snsMessageManager.isPresent()) {
			composite.addResolver(new NotificationMessageHandlerMethodArgumentResolver(snsMessageManager.get()));
		}
		else {
			composite.addResolver(new NotificationMessageHandlerMethodArgumentResolver(null));
		}
		composite.addResolver(new NotificationSubjectHandlerMethodArgumentResolver());
		return composite;
	}

}
