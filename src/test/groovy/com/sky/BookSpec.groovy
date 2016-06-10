package com.sky

import groovy.json.JsonBuilder
import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import groovy.json.StreamingJsonBuilder
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

    def 'Read json slurper'() {
        given:
            JsonSlurper js = new JsonSlurper()
        when:
            def result = js.parse(new URL("http://safe-plains-5453.herokuapp.com/bill.json"), "Utf-8")
        then:
            result.total == 136.03

    }

    def 'jsonout using expando'() {
        given:
            Expando expando = new Expando()
            expando.name = 'Thando'
            expando.age = 45
            expando.list = ['a', 'b','c']
        when:
            String result = JsonOutput.toJson(expando)

        then:
            println result
            result.contains('Thando')
    }

    def 'jsonbuildert test'() {
        given:
            JsonBuilder builder = new JsonBuilder()
            builder.record {
                car {
                    name "Honda"
                    make 'Holden'
                    year 2006
                    country 'Australia'

                }
            }
        when:
            String json = JsonOutput.prettyPrint(builder.toString())
        then:
            println json
            json.contains('Honda')


    }

    def 'StreamingJsonBuilder test'() {
        given:
            StringWriter writer = new StringWriter()
            StreamingJsonBuilder builder = new StreamingJsonBuilder(writer)
            builder.person {
                name 'Thando'
                age 34
            }
        when:
            String json = JsonOutput.prettyPrint(writer.toString())
        then:
            json.contains('Thando')
            println json
    }
}
