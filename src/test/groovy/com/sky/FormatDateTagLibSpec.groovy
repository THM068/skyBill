package com.sky

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.GroovyPageUnitTestMixin} for usage instructions
 */
@TestFor(FormatDateTagLib)
class FormatDateTagLibSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "Format date to day and month"() {
        expect:
        applyTemplate('<sky:dayMonthDate dateString="${dateString}" />',[dateString: '2015-01-11'])  == '11 Jan'
    }

    void "Format date to day and month Year"() {
        expect:
        applyTemplate('<sky:dayMonthYearDate dateString="${dateString}" />',[dateString: '2015-01-11'])  == '11 Jan 2015'
    }
}
