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

import java.util.ArrayList;
import java.util.List;

import org.apache.ode.jacob.examples.helloworld.HelloWorld;
import org.apache.ode.jacob.soup.jackson.JacksonExecutionQueueImpl;
import org.apache.ode.jacob.soup.jackson.JacobModule;
import org.apache.ode.jacob.vpu.JacobVPU;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Simple testcase to verify the jackson based serialization/
 * deserialization code.
 * 
 * @author Tammo van Lessen
 *
 */
public class JacksonSoupTest {
    
    private ObjectMapper mapper;
    private JacksonExecutionQueueImpl queue;
    private List<String> fixtures = new ArrayList<String>();
    
    @Before
    public void setUp() {
        mapper = new ObjectMapper();
        mapper.registerModule(new JacobModule());
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        
        queue = new JacksonExecutionQueueImpl();
        
        fixtures.add("{\"objIdCounter\":2,\"currentCycle\":1,\"continuations\":[{\"target\":{\"@class\":\"org.apache.ode.jacob.examples.helloworld.HelloWorld$PrinterProcess\",\"@id\":1,\"_in\":{\"@class\":\"org.apache.ode.jacob.Val\",\"channelType\":\"org.apache.ode.jacob.Val\",\"channelId\":1}},\"method\":\"run\",\"args\":[]},{\"target\":{\"@class\":\"org.apache.ode.jacob.examples.helloworld.HelloWorld$ForwarderProcess\",\"@id\":1,\"in\":{\"@class\":\"org.apache.ode.jacob.Val\",\"channelType\":\"org.apache.ode.jacob.Val\",\"channelId\":2},\"out\":{\"@class\":\"org.apache.ode.jacob.Val\",\"channelType\":\"org.apache.ode.jacob.Val\",\"channelId\":1}},\"method\":\"run\",\"args\":[]},{\"target\":{\"@class\":\"org.apache.ode.jacob.examples.helloworld.HelloWorld$StringEmitterProcess\",\"@id\":1,\"str\":\"Hello\",\"to\":{\"@class\":\"org.apache.ode.jacob.Val\",\"channelType\":\"org.apache.ode.jacob.Val\",\"channelId\":2}},\"method\":\"run\",\"args\":[]},{\"target\":{\"@class\":\"org.apache.ode.jacob.examples.helloworld.HelloWorld$StringEmitterProcess\",\"@id\":1,\"str\":\"World\",\"to\":{\"@class\":\"org.apache.ode.jacob.Val\",\"channelType\":\"org.apache.ode.jacob.Val\",\"channelId\":2}},\"method\":\"run\",\"args\":[]}],\"channels\":[{\"@id\":1,\"type\":\"org.apache.ode.jacob.Val\",\"id\":1,\"refCount\":0,\"replicatedSend\":false,\"replicatedRecv\":false,\"objFrames\":[],\"msgFrames\":[],\"description\":\"simpleHelloWorld-out\"},{\"@id\":2,\"type\":\"org.apache.ode.jacob.Val\",\"id\":2,\"refCount\":0,\"replicatedSend\":false,\"replicatedRecv\":false,\"objFrames\":[],\"msgFrames\":[],\"description\":\"simpleHelloWorld-x\"}],\"global\":null}");
        fixtures.add("{\"objIdCounter\":2,\"currentCycle\":2,\"continuations\":[{\"target\":{\"@class\":\"org.apache.ode.jacob.examples.helloworld.HelloWorld$ForwarderProcess\",\"@id\":1,\"in\":{\"@class\":\"org.apache.ode.jacob.Val\",\"channelType\":\"org.apache.ode.jacob.Val\",\"channelId\":2},\"out\":{\"@class\":\"org.apache.ode.jacob.Val\",\"channelType\":\"org.apache.ode.jacob.Val\",\"channelId\":1}},\"method\":\"run\",\"args\":[]},{\"target\":{\"@class\":\"org.apache.ode.jacob.examples.helloworld.HelloWorld$StringEmitterProcess\",\"@id\":1,\"str\":\"Hello\",\"to\":{\"@class\":\"org.apache.ode.jacob.Val\",\"channelType\":\"org.apache.ode.jacob.Val\",\"channelId\":2}},\"method\":\"run\",\"args\":[]},{\"target\":{\"@class\":\"org.apache.ode.jacob.examples.helloworld.HelloWorld$StringEmitterProcess\",\"@id\":1,\"str\":\"World\",\"to\":{\"@class\":\"org.apache.ode.jacob.Val\",\"channelType\":\"org.apache.ode.jacob.Val\",\"channelId\":2}},\"method\":\"run\",\"args\":[]}],\"channels\":[{\"@id\":1,\"type\":\"org.apache.ode.jacob.Val\",\"id\":1,\"refCount\":0,\"replicatedSend\":false,\"replicatedRecv\":true,\"objFrames\":[{\"@id\":2,\"commGroupFrame\":{\"@id\":3,\"replicated\":true,\"commFrames\":[2]},\"channelFrame\":1,\"_continuation\":{\"@class\":\"org.apache.ode.jacob.examples.helloworld.HelloWorld$PrinterProcess$PrinterProcessReceiveProcess\",\"@id\":4,\"receiver\":{\"@class\":\"org.apache.ode.jacob.examples.helloworld.HelloWorld$PrinterProcess$PrinterProcessVal\",\"@id\":5}}}],\"msgFrames\":[],\"description\":\"simpleHelloWorld-out\"},{\"@id\":6,\"type\":\"org.apache.ode.jacob.Val\",\"id\":2,\"refCount\":0,\"replicatedSend\":false,\"replicatedRecv\":false,\"objFrames\":[],\"msgFrames\":[],\"description\":\"simpleHelloWorld-x\"}],\"global\":null}");
        fixtures.add("{\"objIdCounter\":2,\"currentCycle\":3,\"continuations\":[{\"target\":{\"@class\":\"org.apache.ode.jacob.examples.helloworld.HelloWorld$StringEmitterProcess\",\"@id\":1,\"str\":\"Hello\",\"to\":{\"@class\":\"org.apache.ode.jacob.Val\",\"channelType\":\"org.apache.ode.jacob.Val\",\"channelId\":2}},\"method\":\"run\",\"args\":[]},{\"target\":{\"@class\":\"org.apache.ode.jacob.examples.helloworld.HelloWorld$StringEmitterProcess\",\"@id\":1,\"str\":\"World\",\"to\":{\"@class\":\"org.apache.ode.jacob.Val\",\"channelType\":\"org.apache.ode.jacob.Val\",\"channelId\":2}},\"method\":\"run\",\"args\":[]}],\"channels\":[{\"@id\":1,\"type\":\"org.apache.ode.jacob.Val\",\"id\":1,\"refCount\":0,\"replicatedSend\":false,\"replicatedRecv\":true,\"objFrames\":[{\"@id\":2,\"commGroupFrame\":{\"@id\":3,\"replicated\":true,\"commFrames\":[2]},\"channelFrame\":1,\"_continuation\":{\"@class\":\"org.apache.ode.jacob.examples.helloworld.HelloWorld$PrinterProcess$PrinterProcessReceiveProcess\",\"@id\":4,\"receiver\":{\"@class\":\"org.apache.ode.jacob.examples.helloworld.HelloWorld$PrinterProcess$PrinterProcessVal\",\"@id\":5}}}],\"msgFrames\":[],\"description\":\"simpleHelloWorld-out\"},{\"@id\":6,\"type\":\"org.apache.ode.jacob.Val\",\"id\":2,\"refCount\":0,\"replicatedSend\":false,\"replicatedRecv\":true,\"objFrames\":[{\"@id\":7,\"commGroupFrame\":{\"@id\":8,\"replicated\":true,\"commFrames\":[7]},\"channelFrame\":6,\"_continuation\":{\"@class\":\"org.apache.ode.jacob.examples.helloworld.HelloWorld$ForwarderProcess$ForwarderProcessReceiveProcess\",\"@id\":9,\"receiver\":{\"@class\":\"org.apache.ode.jacob.examples.helloworld.HelloWorld$ForwarderProcess$ForwarderProcessVal\",\"@id\":10,\"out\":{\"@class\":\"org.apache.ode.jacob.Val\",\"channelType\":\"org.apache.ode.jacob.Val\",\"channelId\":1}}}}],\"msgFrames\":[],\"description\":\"simpleHelloWorld-x\"}],\"global\":null}");
        fixtures.add("{\"objIdCounter\":2,\"currentCycle\":4,\"continuations\":[{\"target\":{\"@class\":\"org.apache.ode.jacob.examples.helloworld.HelloWorld$StringEmitterProcess\",\"@id\":1,\"str\":\"World\",\"to\":{\"@class\":\"org.apache.ode.jacob.Val\",\"channelType\":\"org.apache.ode.jacob.Val\",\"channelId\":2}},\"method\":\"run\",\"args\":[]},{\"target\":{\"@class\":\"org.apache.ode.jacob.examples.helloworld.HelloWorld$ForwarderProcess$ForwarderProcessReceiveProcess\",\"@id\":1,\"receiver\":{\"@class\":\"org.apache.ode.jacob.examples.helloworld.HelloWorld$ForwarderProcess$ForwarderProcessVal\",\"@id\":2,\"out\":{\"@class\":\"org.apache.ode.jacob.Val\",\"channelType\":\"org.apache.ode.jacob.Val\",\"channelId\":1}}},\"method\":\"val\",\"args\":[\"Hello\"]}],\"channels\":[{\"@id\":1,\"type\":\"org.apache.ode.jacob.Val\",\"id\":1,\"refCount\":0,\"replicatedSend\":false,\"replicatedRecv\":true,\"objFrames\":[{\"@id\":2,\"commGroupFrame\":{\"@id\":3,\"replicated\":true,\"commFrames\":[2]},\"channelFrame\":1,\"_continuation\":{\"@class\":\"org.apache.ode.jacob.examples.helloworld.HelloWorld$PrinterProcess$PrinterProcessReceiveProcess\",\"@id\":4,\"receiver\":{\"@class\":\"org.apache.ode.jacob.examples.helloworld.HelloWorld$PrinterProcess$PrinterProcessVal\",\"@id\":5}}}],\"msgFrames\":[],\"description\":\"simpleHelloWorld-out\"},{\"@id\":6,\"type\":\"org.apache.ode.jacob.Val\",\"id\":2,\"refCount\":0,\"replicatedSend\":false,\"replicatedRecv\":true,\"objFrames\":[{\"@id\":7,\"commGroupFrame\":{\"@id\":8,\"replicated\":true,\"commFrames\":[7]},\"channelFrame\":6,\"_continuation\":{\"@class\":\"org.apache.ode.jacob.examples.helloworld.HelloWorld$ForwarderProcess$ForwarderProcessReceiveProcess\",\"@id\":9,\"receiver\":{\"@class\":\"org.apache.ode.jacob.examples.helloworld.HelloWorld$ForwarderProcess$ForwarderProcessVal\",\"@id\":10,\"out\":{\"@class\":\"org.apache.ode.jacob.Val\",\"channelType\":\"org.apache.ode.jacob.Val\",\"channelId\":1}}}}],\"msgFrames\":[],\"description\":\"simpleHelloWorld-x\"}],\"global\":null}");
        fixtures.add("{\"objIdCounter\":2,\"currentCycle\":5,\"continuations\":[{\"target\":{\"@class\":\"org.apache.ode.jacob.examples.helloworld.HelloWorld$ForwarderProcess$ForwarderProcessReceiveProcess\",\"@id\":1,\"receiver\":{\"@class\":\"org.apache.ode.jacob.examples.helloworld.HelloWorld$ForwarderProcess$ForwarderProcessVal\",\"@id\":2,\"out\":{\"@class\":\"org.apache.ode.jacob.Val\",\"channelType\":\"org.apache.ode.jacob.Val\",\"channelId\":1}}},\"method\":\"val\",\"args\":[\"Hello\"]},{\"target\":{\"@class\":\"org.apache.ode.jacob.examples.helloworld.HelloWorld$ForwarderProcess$ForwarderProcessReceiveProcess\",\"@id\":1,\"receiver\":{\"@class\":\"org.apache.ode.jacob.examples.helloworld.HelloWorld$ForwarderProcess$ForwarderProcessVal\",\"@id\":2,\"out\":{\"@class\":\"org.apache.ode.jacob.Val\",\"channelType\":\"org.apache.ode.jacob.Val\",\"channelId\":1}}},\"method\":\"val\",\"args\":[\"World\"]}],\"channels\":[{\"@id\":1,\"type\":\"org.apache.ode.jacob.Val\",\"id\":1,\"refCount\":0,\"replicatedSend\":false,\"replicatedRecv\":true,\"objFrames\":[{\"@id\":2,\"commGroupFrame\":{\"@id\":3,\"replicated\":true,\"commFrames\":[2]},\"channelFrame\":1,\"_continuation\":{\"@class\":\"org.apache.ode.jacob.examples.helloworld.HelloWorld$PrinterProcess$PrinterProcessReceiveProcess\",\"@id\":4,\"receiver\":{\"@class\":\"org.apache.ode.jacob.examples.helloworld.HelloWorld$PrinterProcess$PrinterProcessVal\",\"@id\":5}}}],\"msgFrames\":[],\"description\":\"simpleHelloWorld-out\"}],\"global\":null}");
        fixtures.add("{\"objIdCounter\":2,\"currentCycle\":6,\"continuations\":[{\"target\":{\"@class\":\"org.apache.ode.jacob.examples.helloworld.HelloWorld$ForwarderProcess$ForwarderProcessReceiveProcess\",\"@id\":1,\"receiver\":{\"@class\":\"org.apache.ode.jacob.examples.helloworld.HelloWorld$ForwarderProcess$ForwarderProcessVal\",\"@id\":2,\"out\":{\"@class\":\"org.apache.ode.jacob.Val\",\"channelType\":\"org.apache.ode.jacob.Val\",\"channelId\":1}}},\"method\":\"val\",\"args\":[\"World\"]},{\"target\":{\"@class\":\"org.apache.ode.jacob.examples.helloworld.HelloWorld$PrinterProcess$PrinterProcessReceiveProcess\",\"@id\":1,\"receiver\":{\"@class\":\"org.apache.ode.jacob.examples.helloworld.HelloWorld$PrinterProcess$PrinterProcessVal\",\"@id\":2}},\"method\":\"val\",\"args\":[\"Hello\"]}],\"channels\":[{\"@id\":1,\"type\":\"org.apache.ode.jacob.Val\",\"id\":1,\"refCount\":0,\"replicatedSend\":false,\"replicatedRecv\":true,\"objFrames\":[{\"@id\":2,\"commGroupFrame\":{\"@id\":3,\"replicated\":true,\"commFrames\":[2]},\"channelFrame\":1,\"_continuation\":{\"@class\":\"org.apache.ode.jacob.examples.helloworld.HelloWorld$PrinterProcess$PrinterProcessReceiveProcess\",\"@id\":4,\"receiver\":{\"@class\":\"org.apache.ode.jacob.examples.helloworld.HelloWorld$PrinterProcess$PrinterProcessVal\",\"@id\":5}}}],\"msgFrames\":[],\"description\":\"simpleHelloWorld-out\"}],\"global\":null}");
        fixtures.add("{\"objIdCounter\":2,\"currentCycle\":7,\"continuations\":[{\"target\":{\"@class\":\"org.apache.ode.jacob.examples.helloworld.HelloWorld$PrinterProcess$PrinterProcessReceiveProcess\",\"@id\":1,\"receiver\":{\"@class\":\"org.apache.ode.jacob.examples.helloworld.HelloWorld$PrinterProcess$PrinterProcessVal\",\"@id\":2}},\"method\":\"val\",\"args\":[\"Hello\"]},{\"target\":{\"@class\":\"org.apache.ode.jacob.examples.helloworld.HelloWorld$PrinterProcess$PrinterProcessReceiveProcess\",\"@id\":1,\"receiver\":{\"@class\":\"org.apache.ode.jacob.examples.helloworld.HelloWorld$PrinterProcess$PrinterProcessVal\",\"@id\":2}},\"method\":\"val\",\"args\":[\"World\"]}],\"channels\":[],\"global\":null}");
        fixtures.add("{\"objIdCounter\":2,\"currentCycle\":8,\"continuations\":[{\"target\":{\"@class\":\"org.apache.ode.jacob.examples.helloworld.HelloWorld$PrinterProcess$PrinterProcessReceiveProcess\",\"@id\":1,\"receiver\":{\"@class\":\"org.apache.ode.jacob.examples.helloworld.HelloWorld$PrinterProcess$PrinterProcessVal\",\"@id\":2}},\"method\":\"val\",\"args\":[\"World\"]}],\"channels\":[],\"global\":null}");
        fixtures.add("{\"objIdCounter\":2,\"currentCycle\":9,\"continuations\":[],\"channels\":[],\"global\":null}");
    }
    
    @Test
    public void testEmptySerialization() throws Exception {
        Assert.assertEquals("{\"objIdCounter\":0,\"currentCycle\":0,\"continuations\":[],\"channels\":[],\"global\":null}", mapper.writeValueAsString(queue));
    }

    
    /**
     * Serializes every execution step and compares it with our fixtures.
     * 
     * @throws Exception
     */
    @Test
    public void testSimpleHelloWorldSerialize() throws Exception {
        JacobVPU vpu = new JacobVPU();
        vpu.setContext(queue);
        vpu.inject(new HelloWorld() {
            @Override
            public void run() {
                simpleHelloWorld();
            }
        });
        
        int i = 0;
        while (vpu.execute()) {
            String ser = mapper.writeValueAsString(queue);
            // FIXME: Only for debugging, remove again!
            System.out.println(ser);
            System.out.println(fixtures.get(i));
            Assert.assertEquals(fixtures.get(i), ser);
            i++;
        }

        Assert.assertEquals(9, i);
    }
    
    /**
     * Deserializes each execution step from fixtures and resumes the execution from
     * each point.
     * 
     * @throws Exception
     */
    @Test
    public void testSimpleHelloWorldDeserialize() throws Exception {
        JacobVPU vpu = new JacobVPU();
        
        for (String state : fixtures) {
            vpu.setContext(mapper.readValue(state, JacksonExecutionQueueImpl.class));
            int i = 0;
            while (vpu.execute()) {
                i++;
            }
            
            // sum of pre-loaded & then-completed steps is always 8.
            Assert.assertEquals(8, i + fixtures.indexOf(state));
        }
        
    }
}
