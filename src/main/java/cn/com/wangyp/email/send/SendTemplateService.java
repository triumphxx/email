package cn.com.wangyp.email.send;

import cn.com.wangyp.email.constant.ConstantEnum;
import cn.com.wangyp.email.entity.AddressList;
import cn.com.wangyp.email.entity.EmailElement;
import cn.com.wangyp.email.tools.Transmitter;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static cn.com.wangyp.email.tools.ConvertUtil.convert;

/**
 * @author:wangyupeng
 * @Date:2019-12-08
 * @Time:10:40
 * @desc:
 **/
@Service
public class SendTemplateService {


    @Autowired
    EmailElement emailElement;
    @Autowired
    Transmitter transmitter;

    public  String sendTemplate() {
        String msg = "";
        try {
            List<AddressList> data1List = new ArrayList<>();
            data1List.add(new AddressList("550902023@qq.com", "1111", "1112222"));
            final String xsmtpapi = convert(data1List);
            //普通模板发送的地址url
            HttpPost httpPost = new HttpPost(emailElement.getUrlTemplate());
            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("apiUser", emailElement.getApiUser()));
            params.add(new BasicNameValuePair("apiKey", emailElement.getApiKey()));
            params.add(new BasicNameValuePair("xsmtpapi", xsmtpapi));
            params.add(new BasicNameValuePair("templateInvokeName", emailElement.getTemplateInvokeName()));
            params.add(new BasicNameValuePair("from", emailElement.getFromAddress()));
            params.add(new BasicNameValuePair("fromName", emailElement.getFromName()));
            params.add(new BasicNameValuePair("subject", emailElement.getSubject()));

            httpPost.setEntity(new UrlEncodedFormEntity(params, emailElement.getEncodedType()));
            //进行发送
            msg = transmitter.transmitter(httpPost);
        } catch (Exception e) {
            msg = ConstantEnum.FAIL.getDescription();
        }
        return msg;
    }


}
