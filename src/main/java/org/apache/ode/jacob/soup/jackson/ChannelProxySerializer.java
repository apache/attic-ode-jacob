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
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.ode.jacob.Channel;
import org.apache.ode.jacob.ChannelProxy;
import org.apache.ode.jacob.soup.CommChannel;
import org.apache.ode.jacob.vpu.ChannelFactory;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.WritableTypeId;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.jsontype.impl.ClassNameIdResolver;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

/**
 * Jackson serializer for Channel Proxies.
 * Serializes only channel id and channel type. Also keeps a
 * list of serialized channels, which is needed for the garbage
 * collection of unused channels in the ExecutionQueue serializer.
 * 
 * @author Tammo van Lessen
 *
 */
public class ChannelProxySerializer extends StdSerializer<ChannelProxy>{

    private static final long serialVersionUID = -3021202839880017806L;

    private final Set<Integer> serializedChannels = new LinkedHashSet<Integer>();
    
    protected ChannelProxySerializer() {
        super(ChannelProxy.class);
    }

    @Override
    public void serialize(ChannelProxy value, JsonGenerator jgen,
            SerializerProvider provider) throws IOException,
            JsonGenerationException {
        jgen.writeStartObject();
        serializeContents(value, jgen, provider);
        jgen.writeEndObject();
    }
    
    @Override
    public void serializeWithType(ChannelProxy value, JsonGenerator jgen, SerializerProvider provider,
            TypeSerializer typeSer)
        throws IOException, JsonGenerationException
    {
        WritableTypeId typeId = typeSer.typeId(value, JsonToken.START_OBJECT);
        typeSer.writeTypePrefix(jgen, typeId);
        serializeContents(value, jgen, provider);
        typeSer.writeTypeSuffix(jgen, typeId);
    }

    private void serializeContents(ChannelProxy value, JsonGenerator jgen,
            SerializerProvider provider) throws JsonGenerationException, IOException {
        CommChannel commChannel = (CommChannel) ChannelFactory.getBackend((Channel)value);
        ClassNameIdResolver idResolver = new ClassNameIdResolver(provider.constructType(commChannel.getType()), provider.getTypeFactory());
        Integer cid = (Integer)commChannel.getId();
        jgen.writeStringField("channelType", idResolver.idFromBaseType());
        jgen.writeNumberField("channelId", cid);

        // save channel id for garbage collection
        serializedChannels.add(cid);
    }

    public Set<Integer> getSerializedChannels() {
        return serializedChannels;
    }

}
