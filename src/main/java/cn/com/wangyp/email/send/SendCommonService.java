package cn.com.wangyp.email.send;

import cn.com.wangyp.email.constant.ConstantEnum;
import cn.com.wangyp.email.entity.EmailElement;
import cn.com.wangyp.email.tools.Transmitter;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author:wangyupeng
 * @Date:2019-12-08
 * @Time:11:00
 * @desc:
 **/
@Service
public class SendCommonService {

    @Autowired
    EmailElement emailElement;
    @Autowired
    Transmitter transmitter;

    public String sendCommon() {
        String msg = "";
        try {
            //普通模板发送的地址url
            HttpPost httpPost = new HttpPost(emailElement.getUrlCommon());
            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("apiUser", emailElement.getApiUser()));
            params.add(new BasicNameValuePair("apiKey", emailElement.getApiKey()));
            params.add(new BasicNameValuePair("to", emailElement.getRcpt_to()));
            params.add(new BasicNameValuePair("from", emailElement.getFromAddress()));
            params.add(new BasicNameValuePair("fromName", emailElement.getFromName()));
            params.add(new BasicNameValuePair("subject", emailElement.getSubject()));
            params.add(new BasicNameValuePair("html", emailElement.getHtml()));
            httpPost.setEntity(new UrlEncodedFormEntity(params, emailElement.getEncodedType()));
            //进行发送
            msg = transmitter.transmitter(httpPost);
        } catch (Exception e) {
            msg = ConstantEnum.FAIL.getDescription();
        }
        return msg;
    }


}
