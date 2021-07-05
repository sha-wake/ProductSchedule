package com.example.productschedule.bean

data class IpBean(
    val address: String,
    val path: String
    ) {
    override fun equals(other: Any?): Boolean {
        val ip = other as IpBean
        return  address == ip.address && path == ip.path
    }
}
