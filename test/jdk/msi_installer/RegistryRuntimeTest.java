/*
 * Copyright (c) 2021, Red Hat Inc. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

import java.nio.file.Paths;

import static support.Assert.*;
import static support.Install.install;
import static support.Uninstall.uninstall;

/**
 * @test
 */

public class RegistryRuntimeTest {

    private static final String JDK_VER = "17";

    public static void main(String[] args) throws Exception {
        install("ADDLOCAL=jdk_registry_runtime");
        try {

            String scratchDir = Paths.get("").toAbsolutePath().toString();
            assertRegKey("HKLM\\Software\\JavaSoft\\JDK\\" + JDK_VER,
                    "JavaHome", scratchDir + "\\installed\\");
            assertRegKey("HKLM\\Software\\JavaSoft\\JDK\\" + JDK_VER,
                    "RuntimeLib", scratchDir + "\\installed\\bin\\server\\jvm.dll");

            assertPath("installed/bin/java.exe");
            assertPath("installed/bin/server/jvm.dll");
            assertPath("installed/lib/modules");

        } finally {
            uninstall();
        }
    }
}
