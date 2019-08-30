package com.squirrel.controller;

import static nl.captcha.Captcha.NAME;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.squirrel.dto.SetTextProducer;

import nl.captcha.Captcha;
import nl.captcha.audio.AudioCaptcha;
import nl.captcha.backgrounds.GradiatedBackgroundProducer;
import nl.captcha.servlet.CaptchaServletUtil;
import nl.captcha.text.producer.NumbersAnswerProducer;
import nl.captcha.text.renderer.DefaultWordRenderer;

@Controller
public class CaptchaController {

	private static int width = 160; // 이미지 가로크기
	private static int height = 50; // 이미지 높이
	
	// 폰트, 색를 생성해주는 Captcha
	@RequestMapping("/captcha")
	public void captCha(HttpServletRequest request, HttpServletResponse response) {
		
		// 폰트, 컬러 설정부분 ===================================
		
		// java.awt.Font를 사용하여 font를 list에 담는다.
		List<Font> fontList = new ArrayList<Font>();
		fontList.add(new Font("", Font.HANGING_BASELINE, 40));
		fontList.add(new Font("Courier", Font.ITALIC, 40));
		fontList.add(new Font("", Font.PLAIN, 40));
		
		// java.awt.Color를 사용하여 color를 list에 담는다.
		List<Color> colorList = new ArrayList<Color>();
		colorList.add(Color.BLACK);
		colorList.add(Color.GREEN);
		colorList.add(Color.BLUE);
		colorList.add(Color.RED);
		
		// =================================================
		
		Captcha captcha = new Captcha.Builder(width, height)
				/* .addtext() 또는 아래와 같이 정의 
				 * 6자리 숫자, 컬러, 폰트를 설정한다.*/				
				.addText(new NumbersAnswerProducer(6), new DefaultWordRenderer(colorList, fontList))
				.gimp()
				/* BlockGimpyRenderer,FishEyeGimpyRenderer,RippleGimpyRenderer,
				 * ShearGimpyRenderer,StretchGimpyRenderer */
				.addNoise().addBorder()
				.addBackground(new GradiatedBackgroundProducer())
				/* FlatColorBackgroundProducer,SquigglesBackgroundProducer,
				 * TransparentBackgroundProducer */
				.build();
		
		// View에서 CaptCha 객체에 접근 할 수 있도록 Session에 저장한다.
		
		request.getSession().setAttribute(NAME, captcha); 
		CaptchaServletUtil.writeImage(response, captcha.getImage());
	}// end captCha
	
	// audio를 생성해주는 Captcha
	@RequestMapping("/audioCaptcha")
	public void audioCaptcha(HttpServletRequest request, HttpServletResponse response, 
									HttpSession session) {
		
		// Captcha.NAME = 'simpleCaptcha'
		Captcha captcha = (Captcha)session.getAttribute(Captcha.NAME);
		String getAnswer = captcha.getAnswer();
		
		if ( getAnswer == null || getAnswer.equals("") ) {
			getAnswer = captcha.getAnswer();
		}
		
		AudioCaptcha audiocaptcha = new AudioCaptcha.Builder()
				/* .addAnswer(new defaultTextProducer(6, getAnswer.toCharArray())) 
				 * 또는 다음과 같이... */
				.addAnswer(new SetTextProducer(getAnswer))
				.addNoise() // 잡음 추가
				.build();
		
		CaptchaServletUtil.writeAudio(response, audiocaptcha.getChallenge());		
	}// end audioCaptCha
	
	@RequestMapping("/captchaConfirm")
	@ResponseBody
	public int captchaConfirm(@RequestParam("captchaAnswer" )String answer, 
									HttpServletRequest request, HttpServletResponse response) {
		
		Captcha captcha = (Captcha)request.getSession().getAttribute(Captcha.NAME);
		int result = 0;
		
		if( answer != null && !"".equals(answer) ) {
			if( captcha.isCorrect(answer) ) { // 사용자가 입력한 문자열과 Captcha 클래스가 생성한 문자열
				request.getSession().removeAttribute(Captcha.NAME);
			}else {
				result = 1 ;
			}
		}		
		return result;
	}// end captchaConfirm
	
}
