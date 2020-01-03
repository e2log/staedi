/*******************************************************************************
 * Copyright 2017 xlate.io LLC, http://www.xlate.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package io.xlate.edi.internal.stream.validation;

import io.xlate.edi.internal.stream.tokenization.ValidationEventHandler;
import io.xlate.edi.schema.EDISyntaxRule;

class ListSyntaxValidator implements SyntaxValidator {

    private static final ListSyntaxValidator singleton = new ListSyntaxValidator();

    private ListSyntaxValidator() {
    }

    static ListSyntaxValidator getInstance() {
        return singleton;
    }

    @Override
    public void validate(EDISyntaxRule syntax, UsageNode structure, ValidationEventHandler handler) {
        SyntaxStatus status = scanSyntax(syntax, structure.getChildren());

        if (status.anchorPresent && status.elementCount == 1) {
            signalConditionError(syntax, structure, handler);
        }
    }
}
