package com.sky

import java.text.SimpleDateFormat

class FormatDateTagLib {

    static namespace = 'sky'

    static defaultEncodeAs = [taglib:'html']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]

    def dayMonthDate = { attrs, body ->
        String dateString = attrs.dateString
        def billDate = new Date().parse("yyyy-MM-dd", dateString)
        SimpleDateFormat dateFormat = new SimpleDateFormat("d MMM")
        out << dateFormat.format(billDate)
    }

    def dayMonthYearDate = { attrs, body ->
        String dateString = attrs.dateString
        def billDate = new Date().parse("yyyy-MM-dd", dateString)
        SimpleDateFormat dateFormat = new SimpleDateFormat("d MMM yyyy")
        out << dateFormat.format(billDate)
    }
}
