/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.sshd.common.util.io.der;

import java.util.List;

import org.apache.sshd.util.test.BaseTestSupport;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * @author <a href="mailto:dev@mina.apache.org">Apache MINA SSHD Project</a>
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(Parameterized.class)   // see https://github.com/junit-team/junit/wiki/Parameterized-tests
public class ASN1ClassTest extends BaseTestSupport {
    private final ASN1Class expected;

    public ASN1ClassTest(ASN1Class expected) {
        this.expected = expected;
    }

    @Parameters(name = "{0}")
    public static List<Object[]> parameters() {
        return parameterize(ASN1Class.VALUES);
    }

    @Test
    public void testFromName() {
        String name = expected.name();
        for (int index = 1, count = name.length(); index <= count; index++) {
            assertSame(name, expected, ASN1Class.fromName(name));
            name = shuffleCase(name);
        }
    }

    @Test // NOTE: this also tests "fromTypeValue" since "fromDERValue" invokes it
    public void testFromDERValue() {
        assertSame(expected, ASN1Class.fromDERValue((expected.getClassValue() << 6) & 0xFF));
    }
}
