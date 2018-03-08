/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.ode.jacob.soup.jackson;

import java.io.IOException;

import org.apache.ode.jacob.soup.Continuation;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.WritableTypeId;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

/**
 * Jackson serializer for Continuation objects.
 * 
 * @author Tammo van Lessen
 *
 */
public class ContinuationSerializer extends StdSerializer<Continuation> {

    private static final long serialVersionUID = 850057543258259908L;

    public ContinuationSerializer() {
        super(Continuation.class);
    }
    
    @Override
    public void serialize(Continuation value, JsonGenerator jgen,
            SerializerProvider provider) throws IOException,
            JsonGenerationException {
        jgen.writeStartObject();
        serializeContents(value, jgen, provider);
        jgen.writeEndObject();
    }

    
    @Override
    public void serializeWithType(Continuation value, JsonGenerator jgen,
            SerializerProvider provider, TypeSerializer typeSer)
            throws IOException, JsonProcessingException {
        WritableTypeId typeId = typeSer.typeId(value, JsonToken.START_OBJECT);
        typeSer.writeTypePrefix(jgen, typeId);
        serializeContents(value, jgen, provider);
        typeSer.writeTypeSuffix(jgen, typeId);
    }
    
    private void serializeContents(Continuation value, JsonGenerator jgen,
            SerializerProvider provider) throws JsonGenerationException, IOException {

        jgen.writeObjectField("target", value.getClosure());
        jgen.writeStringField("method", value.getMethod().getName());
        jgen.writeObjectField("args", value.getArgs());
    }
}