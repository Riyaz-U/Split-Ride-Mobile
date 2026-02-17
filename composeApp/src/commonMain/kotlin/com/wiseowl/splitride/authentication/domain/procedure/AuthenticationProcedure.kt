package com.wiseowl.splitride.authentication.domain.procedure

import com.wiseowl.splitride.authentication.domain.AuthStep
import com.wiseowl.splitride.authentication.domain.AuthStepResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

interface AuthenticationProcedure{
    val currentStep: AuthStep
    var response: AuthStepResponse?

    fun start(): Flow<AuthStep> = callbackFlow {
        while (true){
            send(currentStep)
            val response = awaitResponse()
            send(currentStep.advance(response))
        }
    }

    suspend fun awaitResponse(): AuthStepResponse{
        while(response!= null){
            // Wait for response to be set
        }
        return response!!
    }

    fun handleResponse(response: AuthStepResponse){
        this.response = response
    }
}