package com.yooyu.backend.common.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.springframework.util.StreamUtils;

import com.yooyu.backend.common.constants.CodeConstant;

/**
 * @author shenweiwei
 * @date 2018年1月9日
 */

public class BufferingRequestWrapper extends HttpServletRequestWrapper {
	private final byte[] body;

	public BufferingRequestWrapper(HttpServletRequest request) throws IOException {
		super(request);
		this.body = StreamUtils.copyToByteArray(request.getInputStream());
	}

	@Override
	public BufferedReader getReader() throws IOException {
		return new BufferedReader(new InputStreamReader(getInputStream(), CodeConstant.PROJECT_ENCODING));
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		final ByteArrayInputStream bais = new ByteArrayInputStream(body);
		return new ServletInputStream() {
			
			@Override
			public int read() throws IOException {
				return bais.read();
			}

			@Override
			public boolean isFinished() {
				// TODO Auto-generated method stub
				return true;
			}

			@Override
			public boolean isReady() {
				// TODO Auto-generated method stub
				return true;
			}

			@Override
			public void setReadListener(ReadListener arg0) {
				// TODO Auto-generated method stub
				
			}

		};
	}

}
