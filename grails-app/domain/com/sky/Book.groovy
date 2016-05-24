package com.sky

/**
 * Created by tm1c14 on 24/05/2016.
 */
class Book {

    String name
    Integer old

    static constraints = {
        name(nullable: false)
        old(nullable: false)
    }


}
