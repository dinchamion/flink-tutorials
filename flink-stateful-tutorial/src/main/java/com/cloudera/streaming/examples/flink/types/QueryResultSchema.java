/*
 * Licensed to Cloudera, Inc. under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cloudera.streaming.examples.flink.types;

import org.apache.flink.api.common.typeinfo.TypeHint;
import org.apache.flink.api.common.typeinfo.TypeInformation;

import java.io.IOException;

/**
 * Query result serialization schema for running the example with kafka.
 */
public class QueryResultSchema extends JsonKafkaSerializationSchema<QueryResult> {

	public QueryResultSchema(String topic) {
		super(topic);
	}

	@Override
	public QueryResult deserialize(byte[] message) throws IOException {
		try {
			return OBJECT_MAPPER.readValue(message, QueryResult.class);
		} catch (IOException e) {
			return null;
		}
	}

	@Override
	public TypeInformation<QueryResult> getProducedType() {
		return new TypeHint<QueryResult>() {
		}.getTypeInfo();
	}
}
