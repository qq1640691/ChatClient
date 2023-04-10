package com.example;

import com.example.GUI.TheStage;
import javafx.application.Application;
import javafx.stage.Stage;

import static com.example.Start.Flag;
import static com.example.Tools.Instrument.thealert;

public class Main extends Application {
    public static long interval=0L;
    public static int LIVE=0;
    @Override
    public void start(Stage primaryStage) {
        if(Flag==1)
        {
            thealert("配置下载失败,无法登录",0);
        }
        else {
            com.example.GUI.TheStage.RSApublickey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApwgjHVt3UV07TdCxCjYCCBDVm+oPu851kSu29X52f6WmujMejSmu3DJo5k/LV8L7ywlYTKuqpuCJDw5WMT3MheapKdyEyXQUzdHy3C52O8VwvkzJkNlIfTYhSPQoMTM8T1CQ5v/KawDKgCRrw3bC275OsxDCwoUWEZJgZ7KXcleLUAsDt3EV/8y+NnrMWmFnxeQsbszWLu4+0HbHXW+U1vtC3bnMLysVda/ENHXp3WSs/zA7n/TmRYQtWRODsacjwogBdloCjqDhFUfEd8hypJtUx3yAxE/E9l2jOMY6a2hiOy/4C2RpW9fh2dqVes+kFg7WxOhVnF1Kuu9Gun8NUwIDAQAB";
            com.example.GUI.TheStage.RSAprivatekey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCnCCMdW3dRXTtN0LEKNgIIENWb6g+7znWRK7b1fnZ/paa6Mx6NKa7cMmjmT8tXwvvLCVhMq6qm4IkPDlYxPcyF5qkp3ITJdBTN0fLcLnY7xXC+TMmQ2Uh9NiFI9CgxMzxPUJDm/8prAMqAJGvDdsLbvk6zEMLChRYRkmBnspdyV4tQCwO3cRX/zL42esxaYWfF5CxuzNYu7j7Qdsddb5TW+0LducwvKxV1r8Q0dendZKz/MDuf9OZFhC1ZE4OxpyPCiAF2WgKOoOEVR8R3yHKkm1THfIDET8T2XaM4xjpraGI7L/gLZGlb1+HZ2pV6z6QWDtbE6FWcXUq670a6fw1TAgMBAAECggEAANV2ZhSepV4rnXP/wOx2bneLDB1gg7sASj+8yOqfM9GYt1A/BExjeOpFgh9+2orOJVGAH9iG4GYO48TaDaKLLVXynT4NFFELM4srRQnJrFBhg4mwQz6vxnQ+jqCCvNrOdRo9XEcwSarLgqqWi8zBefBaxeO9B9SQliOJbgBtvMk3XR0oevjyg16voRlwOMgVncKLmi9i66fiKlUVZUM7RN2l+anTSUau/qZV1c+RxfVoTVBalMqm8Fa+uSj4OycZMrhpxw114mlyFNvNC17XiKJZiqGmFU0Hh+QWfolzYKqFTCdBhR2AWXrCrdmWrQQ3ilBQ8R9m2GmB/WtfbBlvqQKBgQDPfsOuHGYScgXxr1/4z8KIVGCorAUaAMk18aX9D+4yMrVxpajvA9ruKDxrKLOO91xI9IfJ10IYhodOhp2JC3HBcwsOdqZy3GAJAqpjIHJaYvP1MUQA6Bcoqal65SAa/E1GtObDHa/gh2aU7NqRTtoDf+psxyitz7bCZORx9tG/CQKBgQDOE+cEIEA7xjhCMothx6zGyWpz7ljNOzV55lCtV1E9AbZ82+PH1SJMFDO33zuSsXcrAjCMA3ZfbI210QF5tK/1TAKfsSMdCHVnz2nxcYH9BNeeddwhOFWxnoKPYrB+Ba6Alq0Y/ZlOSh5+qDGNi1//5OSz+6dsehV1vnrZWcMkewKBgAxvtJrC6PtCCkkqEb5/9VDmfcSeSs/8LBMnoKNCSSyJDYb6Pt3UhVKw5q4lil2PReC5h0n5tORza7ixKNAF95N4qbj8M42+16KynjPCxhfUxnpzsXe8cWbmhFnxUbNMW/VY8lvPc7MpamSSrab36uk6yXorlayVPob4NRj/iiLBAoGAR93K97ZjkDTtU7CayQvAZkbBmBJr546ZdJxsUDcePp9FS7/HbN4YKt30t0/jT6ISA+6sjXd7pYWO2fQ8hnwLzQin9Zbpvus/Pmen86p5AYrZwwopLXCe+eVkdKqfECPdGjzxusBd4V6ZyePP/CO549yoIVhGp97fcwNthjT2h9cCgYEAqINEj6ymLlNUOv29PMteqiHaRXXZL/OFEIrzoXbnYn2MySMiH9xVZyIrYZkLlL2jeJ/s73OmBiLOK6InEuM6rmRgRJ3Ua3OzujZWsJ2YUITSMV+FunAubMMbc/2uWWoxS0yYio4EvqkXmV5uFG7ZD/Md9JGwA0e0TmYb5mBHaZA=";
            TheStage.login();
        }
    }
}
