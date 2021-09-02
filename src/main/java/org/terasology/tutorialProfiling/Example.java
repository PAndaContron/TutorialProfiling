// Copyright 2021 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0

package org.terasology.tutorialProfiling;

import org.terasology.engine.entitySystem.systems.BaseComponentSystem;
import org.terasology.engine.entitySystem.systems.RegisterSystem;
import org.terasology.engine.logic.console.commandSystem.annotations.Command;
import org.terasology.engine.logic.console.commandSystem.annotations.CommandParam;

/**
 * This class contains examples of performance mistakes and how to improve upon them. Each example is a single command which can be run
 * in-game to test the performance of two different ways of accomplishing the same task, and compared using a profiler.
 */
@RegisterSystem
public class Example extends BaseComponentSystem {
    /**
     * This example shows 2 different ways of concatenating a lot of strings. While using the "+" and "+=" operators is simpler, it is also
     * not as efficient because each iteration allocates a new String object since Strings are immutable. Using a StringBuilder avoids this
     * issue.
     */
    @Command(shortDescription = "String concatenation example",
            helpText = "Concatenates a lot of strings through an efficient and inefficient way")
    public String testPerformanceStrConcat(@CommandParam(value = "Whether to use the efficient version or not") boolean efficient) {
        long time = System.currentTimeMillis();
        if (efficient) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 10000; i++) {
                sb.append("Terasology");
            }
            String s = sb.toString();
        } else {
            String s = "";
            for (int i = 0; i < 10000; i++) {
                s += "Terasology";
            }
        }
        time = System.currentTimeMillis() - time;
        return "That took " + time + "ms";
    }
}
