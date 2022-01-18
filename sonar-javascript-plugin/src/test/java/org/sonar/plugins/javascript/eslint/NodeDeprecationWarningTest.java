/*
 * SonarQube JavaScript Plugin
 * Copyright (C) 2011-2022 SonarSource SA
 * mailto:info AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonar.plugins.javascript.eslint;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.sonar.api.utils.log.LogTesterJUnit5;
import org.sonar.api.utils.log.LoggerLevel;

import static org.assertj.core.api.Assertions.assertThat;

public class NodeDeprecationWarningTest {

  private static final String MSG = "You are using Node.js version 8, which reached end-of-life. Support for this version will be dropped in future release, please upgrade Node.js to more recent version.";
  private static final String MSG_10 = "You are using Node.js version 10, which reached end-of-life. Support for this version will be dropped in future release, please upgrade Node.js to more recent version.";
  private static final String MSG_12 = "You are using Node.js version 12, which reached end-of-life. Support for this version will be dropped in future release, please upgrade Node.js to more recent version.";
  private static final String UNSUPPORTED_MSG = "Node.js version 15 is not supported, you might experience issues. Please use a supported version of Node.js [14, 16]";

  @RegisterExtension
  public final LogTesterJUnit5 logTester = new LogTesterJUnit5();

  static class TestAnalysisWarnings extends AnalysisWarningsWrapper {
    List<String> warnings = new ArrayList<>();

    @Override
    public void addUnique(String text) {
      warnings.add(text);
    }
  }

  TestAnalysisWarnings analysisWarnings = new TestAnalysisWarnings();
  NodeDeprecationWarning deprecationWarning = new NodeDeprecationWarning(analysisWarnings);

  @Test
  void test() {
    deprecationWarning.logNodeDeprecation(8);

    assertThat(analysisWarnings.warnings).containsExactly(MSG);
    assertThat(logTester.logs(LoggerLevel.WARN)).contains(MSG);
  }

  @Test
  void test_10() {
    deprecationWarning.logNodeDeprecation(10);

    assertThat(analysisWarnings.warnings).containsExactly(MSG_10);
    assertThat(logTester.logs(LoggerLevel.WARN)).contains(MSG_10);
  }

  @Test
  void test_12() {
    deprecationWarning.logNodeDeprecation(12);

    assertThat(analysisWarnings.warnings).containsExactly(MSG_12);
    assertThat(logTester.logs(LoggerLevel.WARN)).contains(MSG_12);
  }

  @Test
  void test_good_version() {
    deprecationWarning.logNodeDeprecation(14);

    assertThat(analysisWarnings.warnings).isEmpty();
    assertThat(logTester.logs(LoggerLevel.WARN)).isEmpty();
  }

  @Test
  void test_no_warnings() {
    // SonarLint doesn't provide AnalysisWarnings API
    NodeDeprecationWarning deprecationWarning = new NodeDeprecationWarning(new AnalysisWarningsWrapper());
    deprecationWarning.logNodeDeprecation(8);

    assertThat(logTester.logs(LoggerLevel.WARN)).contains(MSG);
  }

  @Test
  void test_unsupported_version() {
    deprecationWarning.logNodeDeprecation(15);

    assertThat(analysisWarnings.warnings).containsExactly(UNSUPPORTED_MSG);
    assertThat(logTester.logs(LoggerLevel.WARN)).contains(UNSUPPORTED_MSG);
  }

}
