package com.wiseowl.splitride.authentication.domain.procedure

import com.wiseowl.splitride.authentication.domain.AuthStep

interface AuthenticationProcedure{
    fun start(): AuthStep
}