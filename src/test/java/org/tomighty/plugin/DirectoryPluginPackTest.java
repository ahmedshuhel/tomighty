/*
 * Copyright (c) 2010-2012 Célio Cidral Junior.
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 */

package org.tomighty.plugin;

import org.junit.Test;
import org.tomighty.io.Directory;
import org.tomighty.plugin.impl.DirectoryPluginPack;

import java.io.File;
import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.tomighty.util.FileUtil.urlOf;

public class DirectoryPluginPackTest {

    private static final TestJars TEST_JARS = TestJars.HELLO_WORLD;

    private static final File JAR1 = TEST_JARS.file("helloworld-plugin.jar");
    private static final File JAR2 = TEST_JARS.file("slf4j-api-1.6.4.jar");
    private static final File JAR3 = TEST_JARS.file("slf4j-simple-1.6.4.jar");

    private static final File[] ALL_JARS = { JAR1, JAR2, JAR3 };

    @Test
    public void jars() throws Exception {
        PluginPack pluginPack = new DirectoryPluginPack(directoryWith(ALL_JARS));

        URL[] urls = pluginPack.jars();

        assertEquals(3, urls.length);
        assertEquals(urlOf(JAR1), urls[0]);
        assertEquals(urlOf(JAR2), urls[1]);
        assertEquals(urlOf(JAR3), urls[2]);
    }

    private Directory directoryWith(File[] jars) {
        Directory directory = mock(Directory.class);
        when(directory.filesByExtension("jar")).thenReturn(jars);
        return directory;
    }


}
