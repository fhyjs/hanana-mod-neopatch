package org.eu.hanana.reimu.hnn.neopatch.schemes;

import org.cef.callback.CefCallback;
import org.cef.handler.CefResourceHandlerAdapter;
import org.cef.misc.IntRef;
import org.cef.misc.StringRef;
import org.cef.network.CefRequest;
import org.cef.network.CefResponse;
import org.eu.hanana.reimu.hnnapp.Datas;
import org.eu.hanana.reimu.hnnapp.Utils;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class SchemeTencent extends CefResourceHandlerAdapter {
    public static final String scheme = "tencent";
    public static final String domain = "groupwpa";

    private byte[] data_;
    private String mime_type_;
    private int offset_ = 0;

    public SchemeTencent() {
        super();
    }

    @Override
    public synchronized boolean processRequest(CefRequest request, CefCallback callback) {
        String url = request.getURL();
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();

            // 检查是否支持打开浏览器的操作
            if (desktop.isSupported(Desktop.Action.BROWSE)) {
                URI uri = null;
                try {
                    uri = new URI(url);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
                try {
                    desktop.browse(uri);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                data_="QQ is ready.Please view the QQ window.".getBytes(StandardCharsets.UTF_8);
            }
        }
        mime_type_="text/html; charset=UTF-8";
        callback.Continue();
        return true;
    }

    @Override
    public void getResponseHeaders(
            CefResponse response, IntRef response_length, StringRef redirectUrl) {
        response.setMimeType(mime_type_);
        response.setStatus(200);
        response.setHeaderByName("Access-Control-Allow-Origin","*",true);

        // Set the resulting response length
        response_length.set(data_.length);
    }

    @Override
    public synchronized boolean readResponse(
            byte[] data_out, int bytes_to_read, IntRef bytes_read, CefCallback callback) {
        boolean has_data = false;

        if (offset_ < data_.length) {
            // Copy the next block of data into the buffer.
            int transfer_size = Math.min(bytes_to_read, (data_.length - offset_));
            System.arraycopy(data_, offset_, data_out, 0, transfer_size);
            offset_ += transfer_size;

            bytes_read.set(transfer_size);
            has_data = true;
        } else {
            offset_ = 0;
            bytes_read.set(0);
        }

        return has_data;
    }
}