package com.eduardozanela.trajectSocialCodeChanllenge.service.impl;

import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.eduardozanela.trajectSocialCodeChanllenge.entity.UserProfile;
import com.eduardozanela.trajectSocialCodeChanllenge.entity.UserProfileHeadings;


@Component
public class WebScraperHelper {
    
	@Value("#{'${webscraper.searchtags}'.split(',')}")
    private List<String> linksSearchTags;
	
	@Value("${webscraper.timeout}")
	private Integer pageParseTimeoutMillis;

    /**
     * Fetches all the links from the page which matches the criteria for linksSearchTags supplied in constructor
     * @return
     * @throws IOException
     */
    public Set<UserProfileHeadings> getAllHeadingsFromPage(String pageUrl, UserProfile user) throws IOException {
        Document doc = Jsoup.parse(new URL(pageUrl), pageParseTimeoutMillis);
        return searchTags(doc, user);
    }
    
    /**
     * Extracts the actual link from a tag
     * @param doc
     * @param searchTags
     * @return
     */
    private Set<UserProfileHeadings> searchTags(Document doc, UserProfile user){
        Set<UserProfileHeadings> headings = new HashSet<>();
        linksSearchTags.forEach(tag->{
            Elements elems = doc.select(tag);
            elems.forEach(e->{
            	headings.add(new UserProfileHeadings(e.select(tag).text(), user));
            });
        });
        return headings;
    }
}