/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jbpm.console.ng.cm.backend.server;

import java.util.function.Function;

import org.jbpm.console.ng.cm.model.CaseInstanceSummary;
import org.kie.server.api.model.cases.CaseInstance;

import static java.util.Collections.emptyList;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

public class CaseInstanceMapper implements Function<CaseInstance, CaseInstanceSummary> {

    @Override
    public CaseInstanceSummary apply(final CaseInstance ci) {
        if (ci == null) {
            return null;
        }

        return CaseInstanceSummary.builder().
                caseId(ci.getCaseId()).
                description(ci.getCaseDescription()).
                status(ci.getCaseStatus()).
                containerId(ci.getContainerId()).
                owner(ci.getCaseOwner()).
                startedAt(ci.getStartedAt()).
                completedAt(ci.getCompletedAt()).
                caseDefinitionId(ci.getCaseDefinitionId()).
                roleAssignments(
                        ofNullable(ci.getRoleAssignments()).orElse(emptyList())
                                .stream()
                                .map(new RoleAssignmentsMapper())
                                .collect(toList())).
                build();
    }

}