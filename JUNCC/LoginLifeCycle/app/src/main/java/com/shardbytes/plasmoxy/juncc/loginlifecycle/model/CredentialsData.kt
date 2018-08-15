package com.shardbytes.plasmoxy.juncc.loginlifecycle.model

import java.io.Serializable

data class CredentialsData(val name: String, val passwordHash: String) : Serializable