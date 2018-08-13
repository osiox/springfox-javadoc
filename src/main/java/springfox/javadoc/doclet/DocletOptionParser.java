/*
 *
 *  Copyright 2018-2019 the original author or authors.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 *
 */
package springfox.javadoc.doclet;

class DocletOptionParser {

    static final String DIRECTORY_OPTION = "-d";
    static final String CLASS_DIR_OPTION = "-classdir";
    static final String EXCEPTION_REF_OPTION = "-exceptionRef";
    private final String[][] options;

    DocletOptionParser(String[][] options) {
        this.options = options;
    }

    DocletOptions parse() {
        String propertyFilePath = "";
        Boolean documentExceptions = false;
        for (String[] each : options) {
            if(CLASS_DIR_OPTION.equalsIgnoreCase(each[0])) {
                throw new IllegalArgumentException(CLASS_DIR_OPTION + " option is deprecated. Use " + DIRECTORY_OPTION + " instead.");
            }
            if (EXCEPTION_REF_OPTION.equalsIgnoreCase(each[0])) {
                documentExceptions = Boolean.valueOf(each[1]);
            }
            if (DIRECTORY_OPTION.equalsIgnoreCase(each[0])) {
                propertyFilePath = each[1];
            }
        }

        return new DocletOptionsBuilder()
          .withPropertyFilePath(propertyFilePath)
          .withDocumentExceptions(documentExceptions)
          .build();
    }
}
