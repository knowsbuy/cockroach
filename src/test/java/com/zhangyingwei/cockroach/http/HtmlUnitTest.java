package com.zhangyingwei.cockroach.http;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.IOException;

/**
 * Created by Administrator on 2017/10/30/030.
 */
public class HtmlUnitTest {
    public static void main(String[] args) throws IOException {
        WebClient client = new WebClient();
        HtmlPage page = client.getPage("http://pic.sogou.com/pics?query=%C3%A8&w=05009900&p=40030500&_asf=pic.sogou.com&_ast=1509333507&sc=index&sut=4125&sst0=1509333507216");
        System.out.println("-------------------------------------");
        System.out.println(page.getTitleText());
        System.out.println(page.getBody());
    }
}
