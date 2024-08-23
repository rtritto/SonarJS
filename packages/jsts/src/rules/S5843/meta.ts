/*
 * SonarQube JavaScript Plugin
 * Copyright (C) 2011-2024 SonarSource SA
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

// DO NOT EDIT! This file is autogenerated by "npm run generate-meta"

export const meta = {
  type: 'suggestion',
  docs: {
    description: 'Regular expressions should not be too complicated',
    recommended: true,
    url: 'https://sonarsource.github.io/rspec/#/rspec/S5843/javascript',
    requiresTypeChecking: true,
  },
};

export const sonarKey = 'S5843';
import { JSONSchema4 } from '@typescript-eslint/utils/json-schema';
export const schema = {
  type: 'array',
  minItems: 0,
  maxItems: 2,
  items: [
    {
      type: 'object',
      properties: {
        threshold: {
          type: 'integer',
        },
      },
      additionalProperties: false,
    },
  ],
} as const satisfies JSONSchema4;
