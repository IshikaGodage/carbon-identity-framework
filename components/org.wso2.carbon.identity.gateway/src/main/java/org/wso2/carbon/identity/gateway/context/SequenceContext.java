/*
 * Copyright (c) 2017, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.wso2.carbon.identity.gateway.context;



import org.wso2.carbon.identity.gateway.model.User;

import java.util.ArrayList;
import java.util.List;

public class SequenceContext {

    private int currentStep = 0;

    private RequestPathAuthenticatorContext requestPathAuthenticatorContext = null;
    private List<StepContext> stepContextList = new ArrayList<>();

    public SequenceContext() {

    }

    public RequestPathAuthenticatorContext getRequestPathAuthenticator() {
        return requestPathAuthenticatorContext;
    }

    public void setRequestPathAuthenticator(
            RequestPathAuthenticatorContext requestPathAuthenticatorContext) {
        this.requestPathAuthenticatorContext = requestPathAuthenticatorContext;
    }

    public int getCurrentStep() {
        return currentStep;
    }

    public void setCurrentStep(int currentStep) {
        this.currentStep = currentStep;
    }

    public StepContext getStepContext(int step) {
        StepContext stepAuthenticatorsContext = null;
        if (stepContextList.size() >= step && step > 0) {
            stepAuthenticatorsContext = stepContextList.get(step-1);
        }
        return stepAuthenticatorsContext;
    }

    public StepContext getCurrentStepContext() {
        StepContext stepAuthenticatorsContext = null;
        if (currentStep > 0 &&  stepContextList.size() >= currentStep) {
            stepAuthenticatorsContext = stepContextList.get(stepContextList.size()-1);
        }
        return stepAuthenticatorsContext;
    }

    public StepContext addStepContext() {
        StepContext stepContext = new StepContext();
        stepContext.setStep(++currentStep);
        stepContextList.add(stepContext);
        return stepContext ;
    }


    public static class RequestPathAuthenticatorContext {
        private String authenticatorName;
        private User user;
        private boolean isAuthenticated = false;

        public String getAuthenticatorName() {
            return authenticatorName;
        }

        public void setAuthenticatorName(String authenticatorName) {
            this.authenticatorName = authenticatorName;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public boolean isAuthenticated() {
            return isAuthenticated;
        }

        public void setIsAuthenticated(boolean isAuthenticated) {
            this.isAuthenticated = isAuthenticated;
        }
    }

    public static class StepContext {
        private int step;
        private String authenticatorName;
        private String identityProviderName;
        private User user;
        private boolean isAuthenticated = false;

        public String getAuthenticatorName() {
            return authenticatorName;
        }

        public void setAuthenticatorName(String authenticatorName) {
            this.authenticatorName = authenticatorName;
        }

        public String getIdentityProviderName() {
            return identityProviderName;
        }

        public void setIdentityProviderName(String identityProviderName) {
            this.identityProviderName = identityProviderName;
        }

        public boolean isAuthenticated() {
            return isAuthenticated;
        }

        public void setIsAuthenticated(boolean isAuthenticated) {
            this.isAuthenticated = isAuthenticated;
        }

        public int getStep() {
            return step;
        }

        public void setStep(int step) {
            this.step = step;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }
    }


}
