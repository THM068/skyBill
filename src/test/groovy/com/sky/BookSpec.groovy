package com.sky

import spock.lang.Specification
import grails.buildtestdata.mixin.Build
/**
 * Created by tm1c14 on 24/05/2016.
 */

@Build(Book)
class BookSpec extends Specification {

    def setup() {
        for(int x =1;  x < 20; x++) {
            Book.build(name: "Book_${x}", old: x * 2)
        }
    }

    def 'The older book is how old'() {
        given:
            def criteria = Book.createCriteria()

        when:
            def result = criteria.get {
                projections {
                    max('old')
                }
            }
        then:
            result == 38
    }

    def 'The rowCount of books'() {
        given:
            def c = Book.createCriteria()
        when:
            def result = c.get {
                projections {
                    rowCount()
                }
            }
        then:
            result == 19
    }
}
