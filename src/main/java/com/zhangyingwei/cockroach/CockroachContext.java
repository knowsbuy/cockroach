package com.zhangyingwei.cockroach;

import com.zhangyingwei.cockroach.config.CockroachConfig;
import com.zhangyingwei.cockroach.executer.TaskExecuter;
import com.zhangyingwei.cockroach.executer.TaskQueue;
import com.zhangyingwei.cockroach.http.client.HttpClient;
import com.zhangyingwei.cockroach.http.HttpProxy;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhangyw on 2017/8/10.
 */
public class CockroachContext {

    private CockroachConfig config;
    private int thread = 5;
    private HttpProxy proxy = null;

    public CockroachContext(CockroachConfig config) {
        this.config = config;
    }

    public CockroachContext thread(int thread) {
        this.thread = thread;
        return this;
    }


    public void start(TaskQueue queue) throws IllegalAccessException, InstantiationException {
        ExecutorService service = Executors.newCachedThreadPool();
        this.thread = config.getThread() == 0 ? this.thread : config.getThread();
        for (int i = 0; i < thread; i++) {
            service.execute(new TaskExecuter(queue,this.bulidHttpClient(),this.config.getStore().newInstance()));
        }
    }

    private HttpClient bulidHttpClient() throws IllegalAccessException, InstantiationException {
        if(this.config.getProxys() != null && this.proxy ==null){
            this.proxy = new HttpProxy(this.config.getProxys());
        }
        HttpClient client  = this.config.getHttpClient().newInstance().setProxy(this.proxy);
        client.setCookie(this.config.getCookie());
        client.setHttpHeader(this.config.getHttpHeader());
        return client;
    }
}