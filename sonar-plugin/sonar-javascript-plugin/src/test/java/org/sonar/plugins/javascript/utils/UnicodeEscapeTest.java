/*
 * SonarQube JavaScript Plugin
 * Copyright (C) 2011-2023 SonarSource SA
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
package org.sonar.plugins.javascript.utils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.sonar.plugins.javascript.utils.UnicodeEscape.unicodeEscape;

import org.junit.jupiter.api.Test;

class UnicodeEscapeTest {

  @Test
  void test_unicodeEscape() {
    assertThat(unicodeEscape("test \u0000")).isEqualTo("test \\u0000");
    assertThat(unicodeEscape("Ödmjuk")).isEqualTo("\\u0214dmjuk");
  }
}
