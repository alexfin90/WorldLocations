package com.softdream.exposicily.data.remote

import com.google.gson.annotations.SerializedName

data class DtoLocation(@SerializedName("properties")
                       val property : DtoProperty)
