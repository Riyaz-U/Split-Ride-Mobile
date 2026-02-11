package com.wiseowl.splitride.core.storage

import com.wiseowl.splitride.core.di.platformModule
import com.wiseowl.splitride.core.di.sharedModule
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test


class StorageManagerTest {

    @BeforeTest
    fun setup() {
        // 2. Start Koin before each test
        startKoin {
            modules(sharedModule)
            modules(platformModule)
        }
    }

    @AfterTest
    fun tearDown() {
        // 3. Stop Koin after each test to avoid memory leaks
        stopKoin()
    }


    @Test
    fun testSave(){
    }
}