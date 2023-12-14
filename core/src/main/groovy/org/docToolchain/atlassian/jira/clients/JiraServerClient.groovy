package org.docToolchain.atlassian.jira.clients

import org.apache.hc.client5.http.classic.methods.HttpGet
import org.apache.hc.core5.http.HttpRequest
import org.apache.hc.core5.net.URIBuilder
import org.docToolchain.configuration.ConfigService

class JiraServerClient extends JiraClient {

    protected final String API_PATH = "/rest/api/2"

    JiraServerClient(ConfigService configService) {
        super(configService)
    }

    @Override
    def getIssuesByJql(String jql, String selectedFields){
        URI uri = new URIBuilder(API_PATH + '/search')
            .addParameter('jql', jql)
            .addParameter('maxResults', '1000')
            .addParameter('fields', selectedFields)
            .build()
        HttpRequest get = new HttpGet(uri)
        return callApiAndFailIfNot20x(get)
    }
}
