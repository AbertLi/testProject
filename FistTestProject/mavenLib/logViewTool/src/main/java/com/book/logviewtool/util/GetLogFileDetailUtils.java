package com.book.logviewtool.util;

import android.text.TextUtils;
import android.util.Log;

import com.book.logviewtool.mvvmunit.logdetail.LogReadBean;
import com.book.logviewtool.mvvmunit.logdetail.LogsRetrievedOnceBean;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


/**
 * 读取Log.txt文件的内容
 *
 * @author wuwang
 * @since 2014.11
 */
public class GetLogFileDetailUtils {
    private static String TAG = "GetLogFileDetailUtils";
    //private static String mOldKeywords = "";//上一次的检索关键词
    private static int targetNum = 0;//满足条件的行数
    private static int numberOfRowsRead = 0;//已经检索的行数
    private static int totalNumberOfRows = 0;//文件总行数

    private static GetLogFileDetailUtils gfu;

    private GetLogFileDetailUtils() {
    }

    /**
     * GetLogFileDetailUtils
     *
     * @return GetFilesUtils
     **/
    public static GetLogFileDetailUtils getInstance() {
        if (gfu == null) {
            synchronized (GetLogFileDetailUtils.class) {
                if (gfu == null) {
                    gfu = new GetLogFileDetailUtils();
                }
            }
        }
        return gfu;
    }


    public LogsRetrievedOnceBean getLogList(LogReadBean reqestBean) {
        ArrayList<String> list = null;
        File path = new File(reqestBean.getFilePath());
        if (path.isDirectory() || !path.exists()) {
            Log.e(TAG, "getLogList() The incoming file address does not exist");
            list = new ArrayList<String>();
            list.add("********** No more logs **********");
            return new LogsRetrievedOnceBean(String.valueOf(targetNum),
                    String.valueOf(numberOfRowsRead),
                    String.valueOf(totalNumberOfRows),
                    list);
        } else {
            totalNumberOfRows = readFileLineNumber(reqestBean.getFilePath());
            list = readTargetData(reqestBean);
            if (list == null || list.size() == 0) {
                list = new ArrayList<String>();
                list.add("********** No more logs **********");
            }
            return new LogsRetrievedOnceBean(String.valueOf(targetNum),
                    String.valueOf(numberOfRowsRead),
                    String.valueOf(totalNumberOfRows),
                    list);
        }
    }


    private int readFileLineNumber(String fileName) {
        LineNumberReader lineNumberReader = null;
        try {
            lineNumberReader = new LineNumberReader(new FileReader(fileName));
            lineNumberReader.skip(Long.MAX_VALUE);
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, "readFileLineNumber e =" + e.toString());
        }
        //注意加1，实际上是读取换行符，所以需要+1
        int lineNumber = lineNumberReader.getLineNumber() + 1;
        return lineNumber;
    }


    FileInputStream mFis;
    InputStreamReader mIsr;
    BufferedReader mReader;

    private ArrayList<String> readTargetData(LogReadBean reqestBean) {
        Log.i(TAG,"requestBean toString = "+reqestBean.toString());
        ArrayList<String> listData = new ArrayList<>();
        try {
            if (reqestBean.isNewRequest() || mReader == null) {
                if (mReader != null) {
                    mReader.close();
                }
                mFis = new FileInputStream(reqestBean.getFilePath());
                mIsr = new InputStreamReader(mFis, StandardCharsets.UTF_8);
                mReader = new BufferedReader(mIsr);
                targetNum = 0;
                numberOfRowsRead = 0;
            }

            String line = null;
            while ((line = mReader.readLine()) != null) {
                if (targetNum > reqestBean.getStartLine()) {
                    if (!TextUtils.isEmpty(reqestBean.getKeyWords())) {
                        if (line.contains(reqestBean.getKeyWords())) {
                            targetNum++;
                            listData.add(line);
                        }
                    } else {
                        targetNum++;
                        listData.add(line);
                    }
                } else {
                    targetNum++;
                }
                numberOfRowsRead++;
                if (targetNum > reqestBean.getStartLine() + reqestBean.getOnceNumOfLine()) {
                    break;
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.e(TAG, "readTargetData() e" + e.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            Log.e(TAG, "readTargetData()  e" + e.toString());
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, "readTargetData() readLine() e" + e.toString());
        }
        return listData;
    }
}