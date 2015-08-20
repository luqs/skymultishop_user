package com.sirius.skymall.user.util.base;

import java.util.Date;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import sun.misc.BASE64Encoder;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;

@SuppressWarnings("deprecation")
public class MemCached {
	
	// 创建全局的唯一实例
    protected static MemCachedClient mcc = new MemCachedClient();
   
    protected static MemCached memCached = new MemCached();
   
    // 设置与缓存服务器的连接池
    static {
        // 服务器列表和其权重
    	String ip = ConfigUtil.get("mem_ip");
    	String port = ConfigUtil.get("mem_port");
    	String time = ConfigUtil.get("mem_time");
        String[] servers = {ip+ ":" + port};
        Integer[] weights = {3};

        // 获取socke连接池的实例对象
        SockIOPool pool = SockIOPool.getInstance();

        // 设置服务器信息
        pool.setServers( servers );
        pool.setWeights( weights );

        // 设置初始连接数、最小和最大连接数以及最大处理时间
        pool.setInitConn( 10 );
        pool.setMinConn( 5 );
        pool.setMaxConn( 250 );
        pool.setMaxIdle( 1000 * 60 * 60 * 6 );

        // 设置主线程的睡眠时间
        pool.setMaintSleep( 30 );

        // 设置TCP的参数，连接超时等
        pool.setNagle( false );
        pool.setSocketTO( Integer.parseInt(time) );
        pool.setSocketConnectTO( 0 );
        pool.setAliveCheck(true);  
        // 初始化连接池
        pool.initialize();

    }
   
    /**
     * 保护型构造方法，不允许实例化！
     *
     */
    protected MemCached()
    {
       
    }
   
    /**
     * 获取唯一实例.
     * @return
     */
    public static MemCached getInstance()
    {
        return memCached;
    }
   
    /**
     * 添加一个指定的值到缓存中.
     * @param key
     * @param value
     * @return
     */
    public boolean add(String key, Object value)
    {
    	if(this.get(key)!=null){
    		return this.replace(key, value);
    	}else{
    		 return mcc.add(key, value);
    	}
    }
    public boolean remove(String key)
    {
        return mcc.delete(key);
    }
    public boolean add(String key, Object value, Date expiry)
    {
        return mcc.add(key, value, expiry);
    }
   
    public boolean replace(String key, Object value)
    {
        return mcc.replace(key, value);
    }
   
    public boolean replace(String key, Object value, Date expiry)
    {
        return mcc.replace(key, value, expiry);
    }
   
    /**
     * 根据指定的关键字获取对象.
     * @param key
     * @return
     */
    public Object get(String key)
    {
        return mcc.get(key);
    }
   
    public static void main(String[] args)
    {
//        MemCached cache = MemCached.getInstance();
//        String sourceStr = "0000008801" + "-" + String.valueOf(System.currentTimeMillis());
//		String userToken = new BASE64Encoder().encode(sourceStr.getBytes());
//		cache.add(userToken, "aaa");
//        Object obj = cache.get("MDAwMDAwODgwNy0xNDM1MTEyMjk3NzY1");
        
        String name = getPinYin("卢青松");
    }
    public static String getPinYin(String src) {
        char[] t1 = null;
        t1 = src.toCharArray(); 
        // System.out.println(t1.length);
        String[] t2 = new String[t1.length];
        // System.out.println(t2.length);
        // 设置汉字拼音输出的格式 
        HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();
        t3.setCaseType(HanyuPinyinCaseType.UPPERCASE); 
        t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE); 
        t3.setVCharType(HanyuPinyinVCharType.WITH_V); 
        String t4 = ""; 
        int t0 = t1.length;
        try { 
            for (int i =0; i < t0; i++) { 
                // 判断能否为汉字字符 
                // System.out.println(t1[i]);
               if (Character.toString(t1[i]).matches("[\\u4E00-\\u9FA5]+")) {
                   t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);// 将汉字的几种全拼都存到t2数组中
                   if(i==0){
                	   t4 += t2[0]+" ";// 取出该汉字全拼的第一种读音并连接到字符串t4后
                   }else{
                	   t4 += t2[0];
                   }
                   
               } else {
                   // 如果不是汉字字符，间接取出字符并连接到字符串t4后
                    t4 += Character.toString(t1[i]); 
                } 
            } 
       } catch (BadHanyuPinyinOutputFormatCombination e) {
           e.printStackTrace(); 
        } 
       return t4; 
    } 
}
