package org.party.Service;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fasterxml.jackson.databind.util.BeanUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.party.Common.Enum.BooleanEnum;
import org.party.Mapper.RoomDetailMapper;
import org.party.Mapper.RoomPlayerMapper;
import org.party.Model.LoginResModel;
import org.party.Model.RoomCreateInfo;
import org.party.Model.RoomDetailInfo;
import org.party.Model.RoomJoinInfo;
import org.party.PO.RoomDetailPO;
import org.party.PO.RoomPlayerPO;
import org.party.Utils.HttpUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.EOFException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Description:
 * Author: chentao
 * Date: 02 10月 2024
 *
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final String GRANT_TYPE = "client_credential";
    private final String APPID = "wx702d90e7099140b4";
    private final String SECRET = "325cd43528b36a3f556e744e80bb19f2";
    private final String SIG_METHOD = "hmac_sha256";

    private final RoomDetailMapper roomDetailMapper;
    private final RoomPlayerMapper roomPlayerMapper;
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 登录方法
     * @param loginCode
     * @return
     */
    public LoginResModel login(String loginCode) throws Exception {
        Map<String, String> loginMap = new HashMap<>(){{
            put("appid", "wx702d90e7099140b4");
            put("secret", "325cd43528b36a3f556e744e80bb19f2");
            put("js_code", loginCode);
            put("grant_type", "authorization_code");
        }};
        String url = "https://api.weixin.qq.com/sns/jscode2session";
            Map<String, String> resultMap = JSON.parseObject(new HttpUtils().HttpGet(loginMap, url), HashMap.class);
            if (!ObjectUtils.isEmpty(resultMap.get("errcode"))) {
                throw new Exception("数据失效");
            } else {
                return JSONObject.parseObject(JSONObject.toJSONString(resultMap), LoginResModel.class);
            }
        }

    /**
     * 登录校验
     * @param
     * @return
     */
    public String checkLogin(String openId, String session_key) throws IOException, InterruptedException {

        Map<String, String> checkMap = new HashMap<>(){{
            put("access_token", getAccessToken());
            put("signature", null);
            put("openid", openId);
            put("sig_method", SIG_METHOD);
        }};
        String url = "https://api.weixin.qq.com/wxa/checksession";
        Map<String, String> resultMap = JSON.parseObject(new HttpUtils().HttpGet(checkMap, url), HashMap.class);
        return resultMap.toString();
    }

    /**
     * 获取 接口调用凭证
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public String getAccessToken() throws IOException, InterruptedException {
        Map<String, String> checkMap = new HashMap<>(){{
            put("grant_type", GRANT_TYPE);
            put("appid", APPID);
            put("secret", SECRET);
        }};
        String url = "https://api.weixin.qq.com/cgi-bin/token";
        Map<String, String> resultMap = JSON.parseObject(new HttpUtils().HttpGet(checkMap, url), HashMap.class);
        return resultMap.toString();
    }


    public String getHmacSHA256(String data, String key) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac hmacSha256 = Mac.getInstance("HmacSHA256");
        hmacSha256.init(new SecretKeySpec(key.getBytes(), GRANT_TYPE));
        byte[] bytes = hmacSha256.doFinal(data.getBytes());
        String signature = Base64.getEncoder().encodeToString(bytes);
        System.out.printf(signature);
        return signature;
    }

    public String createRoom(RoomCreateInfo roomCreateInfo) {
        RoomDetailPO roomDetailPO = new RoomDetailPO();
        BeanUtils.copyProperties(roomCreateInfo, roomDetailPO);
        roomDetailMapper.insert(roomDetailPO);
        return roomDetailPO.getId().toString();
    }

    public RoomDetailInfo joinRoom(RoomJoinInfo roomJoinInfo) {
        // 判断房间是否存在
        RoomDetailPO roomDetailPO = roomDetailMapper.selectById(roomJoinInfo.getId());
        if (Objects.isNull(roomDetailPO)) {
            throw new RuntimeException("房间不存在");
        }
        // 判断房间是否已满
        Long playerNum = roomPlayerMapper.selectCount(
                new LambdaQueryWrapper<RoomPlayerPO>().eq(RoomPlayerPO::getRoomId, roomJoinInfo.getId()).eq(RoomPlayerPO::getDeleted, BooleanEnum.FALSE));
        if (playerNum >= roomDetailPO.getPlayerMax()) {
            throw new RuntimeException("房间已满员");
        }
        // 加入房间
        RoomPlayerPO roomPlayerPO = new RoomPlayerPO();
        roomPlayerPO.setRoomId(roomJoinInfo.getId());

        // TODO
//        roomPlayerPO.setUserId(roomJoinInfo.getUserId());
        roomPlayerMapper.insert(roomPlayerPO);
        return null;
    }

    public String send(String roomId, String message) {
        HashMap<String, Object> hashMap = new HashMap();
        // TODO
        hashMap.put("userId", "usreId");
        hashMap.put("sendTime", LocalDateTime.now());
        hashMap.put("message", message);
        redisTemplate.opsForValue().set(roomId, message);
    }


}
