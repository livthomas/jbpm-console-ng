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
package org.jbpm.console.ng.client.screens;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.TextBox;
import org.jboss.errai.common.client.api.Caller;
import org.jboss.errai.common.client.api.RemoteCallback;
import org.jbpm.console.ng.api.TaskAdminService;
import org.jbpm.console.ng.client.i18n.TaskAdminConstants;
import org.uberfire.client.annotations.WorkbenchPartTitle;
import org.uberfire.client.annotations.WorkbenchPartView;
import org.uberfire.client.annotations.WorkbenchScreen;
import org.uberfire.client.mvp.UberView;
import org.uberfire.lifecycle.OnOpen;

@Dependent
@WorkbenchScreen(identifier = TaskAdminSettingsPresenter.SCREEN_ID)
public class TaskAdminSettingsPresenter {

    public static final String SCREEN_ID = "Tasks Admin Settings";

    private TaskAdminConstants constants = TaskAdminConstants.INSTANCE;

    public interface TaskAdminSettingsView extends UberView<TaskAdminSettingsPresenter> {

        void displayNotification(String text);

        TextBox getUserNameText();

        Button getGenerateMockTasksButton();
    }

    @Inject
    TaskAdminSettingsView view;

    @Inject
    Caller<TaskAdminService> taskAdminServices;

    @WorkbenchPartTitle
    public String getTitle() {
        return constants.Tasks_List_Admin();
    }

    @WorkbenchPartView
    public UberView<TaskAdminSettingsPresenter> getView() {
        return view;
    }

    public TaskAdminSettingsPresenter() {
    }

    @PostConstruct
    public void init() {
    }

    public void generateMockTasks(String userName, int amountOfTasks) {
        taskAdminServices.call(
                new RemoteCallback<Long>() {
                    @Override
                    public void callback(Long taskId) {
                        view.displayNotification(constants.TaskSuccessfullyCreated());
                    }
                }
        ).generateMockTasks(userName, amountOfTasks);
    }

    @OnOpen
    public void onOpen() {
        view.getUserNameText().setFocus(true);
    }
}
