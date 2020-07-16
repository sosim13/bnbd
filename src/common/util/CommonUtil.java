package common.util;

import java.io.*;
import java.util.Enumeration;
import javax.servlet.*;
import javax.servlet.http.*;

public class CommonUtil {	

	// 입력받은 스트링을 EUC-KR로 변환해서 돌려줌.
	public String toEucKr(String s) {
		try {
			return new String(s.getBytes("iso-8859-1"), "EUC-KR");
		} catch (Exception e) {
			return s;
		}
	}

	// 입력받은 스트링을 ISO-8859-1로 변환해서 돌려줌
	public String to8859(String s) {
		try {
			return new String(s.getBytes("EUC-KR"), "iso-8859-1");
		} catch (Exception e) {
			return s;
		}
	}

	// 입력받은 스트링이 null 이거나 빈값이라면 두번째입력받은 스트링으로 반환한다., 그렇지 않을경우 trim()한 값을 돌려줌.
	public String toParamStr(String oldStr, String newStr) {
		try {
			if (oldStr == null || oldStr.equals("")) {
				return newStr;
			} else {
				return oldStr.trim();
			}
		} catch (Exception e) {
			return oldStr;
		}
	}

	// 입력받은 스트링이 null이면 빈값(""), 그렇지 않을경우 trim()한 값을 돌려줌.
	public String toNullStr(String s) {
		try {
			if (s == null) {
				return "";
			} else {
				return s.trim();
			}
		} catch (Exception e) {
			return s;
		}
	}

	// 입력받은 스트링이 null이면 &nbsp;를 돌려줌.
	public String toNbsp(String s) {
		try {
			if (s == null || s.equals("")) {
				return "&nbsp;";
			} else {
				if (s.trim().length() > 1) {
					return s;
				}
				return "&nbsp;";
			}
		} catch (Exception e) {
			return s;
		}
	}
	
	// 입력받은 숫자가 null이면 &nbsp;를 돌려줌.
	public String toNbsp(int i) {
		String rtn = null;
		try {
			if (Integer.toString(i).equals("")) {
				return "&nbsp;";
			} else {
				return Integer.toString(i);
			}
		} catch (Exception e) {
			return Integer.toString(i);
		}
	}

	// 입력받은 스트링을 숫자만큼 자르고 뒤에 지정한 문자를 붙여줌.
	public String substring(String str, int len, String tail) {
		String rtn = null;
		try {
			if (str == null) return rtn;
			if (str.length() > len) {
				rtn = str.substring(0, len) + tail;
			} else {
				rtn = str;
			}
		} catch (Exception e) {
			return str;
		}
		return rtn;
	}

	// Get (Map, Photo, Logo) Image File Dir.
	public String getImgDir(String rootpath, String group_code, String type) {
		String rtn = "";
//		rtn = rootpath + group_code + type;
		rtn = rootpath + "/" + group_code + "/" + type;
		if (rootpath == null || 
			rootpath.equals("") || 
			group_code == null || 
			group_code.equals("") || 
			type == null || 
			type.equals("")) {
			rtn = null;
		} else {
			if (! mkdirs(rtn)) rtn = null;
		}

		return rtn;
	}

	// 디렉토리를 만들어줌.
	public boolean mkdirs(String path) {
		boolean rtn = false;
		try {
			File f = new File(path);
			if (f != null) {
				if (! f.isDirectory()) {
					rtn = f.mkdirs();
				}
			}
		} catch (Exception e) {
			rtn = false;
			System.out.println(e.toString());
		}

		return rtn;
	}

	// 숫자 앞에 '0'붙히기
	// str : 원래문자, addstr : 붙히고자 하는 문자, num : 만들고자 하는 length
	public String addStr(String str, String addstr, int num) {
		try {
			if ( str != null && addstr != null && num != 0 ) {
				String temp = "";
				for (int i = 0; i < num-str.length(); i++)
				{
					temp = temp + addstr;
				}
				str = temp + str;
			}
		} catch (Exception e) {
			System.out.println("[buan] addStr(String str, String addstr, int num) Err : " + e.toString());	
		}
		return str;
	}

	/********************************
	* 페이징 print
	* totCnt : 게시물 전체 갯수
	* cPage : 현재 페이지
	* pageCnt : 페이지 표시 갯수
	* listCnt : 리스트 표시 갯수
	* prePageImg : 이전 페이지 이미지
	* prePageText : 이전 페이지 텍스트
	* nextPageImg : 다음 페이지 이미지
	* nextPageText : 다음 페이지 텍스트
	* prePageGroupImg : 이전 페이징 그룹 이미지
	* prePageGroupText : 이전 페이징 그룹 텍스트
	* nextPageGroupImg : 다음 페이징 그룹 이미지
	* nextPageGroupText : 다음 페이징 그룹 텍스트
	*********************************/
	public String getPaging(ServletRequest req, int totCnt, int pageCnt, int listCnt, int cPage, 
		String prePageImg, String prePageText, String nextPageImg, String nextPageText, 
		String prePageGroupImg, String prePageGroupText, String nextPageGroupImg, String nextPageGroupText) {
		String rtn = "";
		rtn += getPagingScript();
		rtn += getPagingForm(req);
		int startPage = 0;
		int endPage = 0;
		int totPageCnt = 0;
		try {
			totPageCnt = totCnt / listCnt;
			if ((totCnt % listCnt) > 0) totPageCnt++;

			startPage = (int)(cPage/pageCnt) * pageCnt;
			if ((cPage%pageCnt) > 0) {
				startPage++;
			} else if ((cPage%pageCnt) == 0) {
				startPage = startPage - (pageCnt - 1);
			}
			if (startPage < 1) startPage = 1;

			endPage = startPage + (pageCnt - 1);
			if (endPage > totPageCnt) endPage = totPageCnt;

			// 이전 그룹 표시
			if (prePageGroupImg != null && ! prePageGroupImg.equals("")) {
				if ((cPage - pageCnt) >= 1) {
					rtn += " <a href=\"javascript:movePage('" + (cPage-pageCnt) + "')\" alt=\"" + prePageGroupText + "\">"
					+ "<img src=\"" + prePageGroupImg + "\" border=\"0\"></a> ";
				} else {
					rtn+= " <img src=\"" + prePageGroupImg + "\" border=\"0\"> ";
				}
			} else {
				if (prePageGroupText != null && ! prePageGroupText.equals("")) {
					if ((cPage - pageCnt) >= 1) {
						rtn += " <a href=\"javascript:movePage('" + (cPage-pageCnt) + "')\" alt=\"" + prePageGroupText + "\">"
						+ prePageGroupText + "</a> ";
					} else {
						rtn += " " + prePageGroupText + " ";
					}
				}
			}

			// 이전 페이지 표시
			if (prePageImg != null && ! prePageImg.equals("")) {
				if ((cPage - 1) >= 1) {
					rtn += " <a href=\"javascript:movePage('" + (cPage-1) + "')\" alt=\"" + prePageText + "\">"
					+ "<img src=\"" + prePageImg + "\" border=\"0\"></a> ";
				} else {
					rtn+= " <img src=\"" + prePageImg + "\" border=\"0\"> ";
				}
			} else {
				if (prePageText != null && ! prePageText.equals("")) {
					if ((cPage - 1) >= 1) {
						rtn += " <a href=\"javascript:movePage('" + (cPage-1) + "')\" alt=\"" + prePageText + "\">"
						+ prePageText + "</a> ";
					} else {
						rtn += " " + prePageText + " ";
					}
				}
			}

			// 페이지 표시
			for (int i = startPage; i <= endPage; i++) {
				if (i == cPage) {
					rtn += " [<font color=red><b>" + i + "</b></font>] ";
				} else {
					rtn += " [<a href=\"javascript:movePage('" + i + "')\">" + i + "</a>] ";
				}
			}

			// 다음 페이지 표시
			if (nextPageImg != null && ! nextPageImg.equals("")) {
				if ((cPage + 1) <= totPageCnt) {
					rtn += " <a href=\"javascript:movePage('" + (cPage+1) + "')\" alt=\"" + nextPageText + "\">"
					+ "<img src=\"" + nextPageImg + "\" border=\"0\"></a> ";
				} else {
					rtn+= " <img src=\"" + nextPageImg + "\" border=\"0\"> ";
				}
			} else {
				if (nextPageText != null && ! nextPageText.equals("")) {
					if ((cPage + 1) <= totPageCnt) {
						rtn += " <a href=\"javascript:movePage('" + (cPage+1) + "')\" alt=\"" + nextPageText + "\">"
						+ nextPageText + "</a> ";
					} else {
						rtn += " " + nextPageText + " ";
					}
				}
			}

			// 다음 그룹 표시
			if (nextPageGroupImg != null && ! nextPageGroupImg.equals("")) {
				if ((cPage + pageCnt) <= totPageCnt) {
					rtn += " <a href=\"javascript:movePage('" + (cPage+pageCnt) + "')\" alt=\"" + nextPageGroupText + "\">"
					+ "<img src=\"" + nextPageGroupImg + "\" border=\"0\"></a> ";
				} else {
					rtn+= " <img src=\"" + nextPageGroupImg + "\" border=\"0\"> ";
				}
			} else {
				if (nextPageGroupText != null && ! nextPageGroupText.equals("")) {
					if ((cPage + pageCnt) <= totPageCnt) {
						rtn += " <a href=\"javascript:movePage('" + (cPage+pageCnt) + "')\" alt=\"" + nextPageGroupText + "\">"
						+ nextPageGroupText + "</a> ";
					} else {
						rtn += " " + nextPageGroupText + " ";
					}
				}
			}

		} catch (Exception e) {
			rtn = null;
			System.out.println("[buan] paging Error : " + e.toString());
		}

		return rtn;
	}
	/******************************************************
	* 페이징 관련 스크립트를 돌려줌
	******************************************************/
	public String getPagingScript() {
		StringBuffer buf = new StringBuffer("");
		buf.append("<script language=\"javascript\">\n");
		buf.append("<!--\n");
		buf.append("  function movePage(pg) { \n");
		buf.append("    document.movePageForm.cpage.value = pg; \n");
		buf.append("    document.movePageForm.submit();\n");
		buf.append("  }\n");
		buf.append("//-->\n");
		buf.append("</script>\n\n");

		return buf.toString(); 
	}

	/******************************************************
	* 페이징 관련 Form을 돌려줌.
	******************************************************/
	public String getPagingForm(ServletRequest req) {
		StringBuffer buf = new StringBuffer("");
		String tmp1 = null;
		String tmp2 = null;
		boolean cpage = false;
		
		Enumeration enu = req.getParameterNames();
		buf.append("<form name=\"movePageForm\" method=\"post\">\n");
		while (enu.hasMoreElements()) {
			tmp1 = (String) enu.nextElement();
			if (tmp1 != null) {
				if (tmp1.equalsIgnoreCase("cpage")) cpage = true;
				tmp2 = req.getParameter(tmp1);
				buf.append("<input type=\"hidden\" name=\""+toEucKr(tmp1)+"\" value=\""+toEucKr(tmp2)+"\">\n");
			}
		}
		if (! cpage) {
			buf.append("<input type=\"hidden\" name=\"cpage\" value=\"1\">\n");
		}
		buf.append("</form>\n");

		return buf.toString(); 
	}

	/******************************************************
	* 숫자 앞의 Zeor Delete 
	******************************************************/
	public String deleteFrontZero(String str) {
		String temp_str = "";	
		try {
	        if ( str != null ) {
				temp_str = new Integer(str).toString();
			}
		} catch (Exception e) {
			System.out.println("[buan] deleteFrontZero(String str) Err : " + e.toString());
		}
		return temp_str;
	}

	public static String replaceStr (String org_str, String search_str, String change_str) {
    	int i = 0;
      	String rStr = "";
		
		while ( org_str.indexOf (search_str) > -1 ) {
        	i = org_str.indexOf (search_str);
            rStr += org_str.substring (0, i) + change_str;
            org_str = org_str.substring (i + search_str.length());
      	}
      	return rStr + org_str;
	}
	
        public static String convertWEBtoDB (String str) {
		try {
			str = replaceStr(str, "&",  "&amp;");
			str = replaceStr(str, "<",  "&lt;");
			str = replaceStr(str, ">",  "&gt;");
			str = replaceStr(str, "\"", "&quot;");
			str = replaceStr(str, "\'", "&#039;");
			str = replaceStr(str, "\\", "&#092;");
			str = replaceStr(str, "\n", "<br>");
		} catch(Exception e){
			System.out.println("[buan] << convertWEBtoDB (String str) >> Error : " + e.getMessage()); 
		}
		
		return str;	
	}
	
	// convert DB type String to WEB type String 
	public static String convertDBtoWEB (String str) {
		try {
			str = replaceStr(str, "&amp;", "&");
			str = replaceStr(str, "&lt;", "<");
			str = replaceStr(str, "&gt;", ">");
			str = replaceStr(str, "&quot;", "\"");
			str = replaceStr(str, "&#039;", "\'");
			str = replaceStr(str, "&#092;", "\\");
			str = replaceStr(str, " ", "&nbsp;");
		} catch(Exception e){
			System.out.println("[buan] << convertWEBtoDB (String str) >> Error : " + e.getMessage()); 
		}
		
		return str;	
	}
	
	// convert DB type String to WEBComponent type String 
	public static String convertDBtoWEBComp (String str) {
		try {
			str = replaceStr(str, "&amp;", "&");
			str = replaceStr(str, "&lt;", "<");
			str = replaceStr(str, "&gt;", ">");
			str = replaceStr(str, "&quot;", "\"");
			str = replaceStr(str, "&#039;", "\'");
			str = replaceStr(str, "&#092;", "\\");
			str = replaceStr(str, "<br>", "\n");
			
		} catch(Exception e){
			System.out.println("[buan] << convertDBtoWEBComp (String str) >> Error : " + e.getMessage()); 
		}
		
		return str;	
	}
	
	public String toNullZero(String s) {
		try {
			if (s == null) {
				return "0";
			} else {
				return s.trim();
			}
		} catch (Exception e) {
			return s;
		}
	}

	public String toPlusZero(String s) {
		try {
			if ((s == null) || (s.length()==0)) {
				return "0";
			} else if(s.length()==1) {
				return "0"+s;			
			} else {
				return s;
			}
		} catch (Exception e) {
			return s;
		}
	}
	
	public String toMinZero(String s) {
		try {
			if (s == null) {
				return "0";
			} else {
				return Integer.parseInt(s)+"";
			}
		} catch (Exception e) {
			return s;
		}
	}
	
	public String toMinuSeqNo(String s) {
		try {
			
			if (s == null) {
				s= "0";
			} else if (Integer.parseInt(s)== 1) {
				s=1+"";
			} else if (Integer.parseInt(s)== 3) {
				s=2+"";
			} else if (Integer.parseInt(s)== 5) {
				s=3+"";
			} else if (Integer.parseInt(s)== 7) {
				s=4+"";
			} else if (Integer.parseInt(s)== 9) {
				s=5+"";
			} else if (Integer.parseInt(s)== 11) {
				s=6+"";
			} else if (Integer.parseInt(s)== 13) {
				s=7+"";
			} else if (Integer.parseInt(s)== 15) {
				s=8+"";
			} else if (Integer.parseInt(s)== 17) {
				s=9+"";
			} else if (Integer.parseInt(s)== 19) {
				s=10+"";
			} else if (Integer.parseInt(s)== 21) {
				s=11+"";
			} else if (Integer.parseInt(s)== 23) {
				s=12+"";
			} else if (Integer.parseInt(s)== 25) {
				s=13+"";
			} else if (Integer.parseInt(s)== 27) {
				s=14+"";
			} else if (Integer.parseInt(s)== 29) {
				s=15+"";
			} else if (Integer.parseInt(s)== 31) {
				s=16+"";
			} else if (Integer.parseInt(s)== 2) {
				s=17+"";
			} else if (Integer.parseInt(s)== 4) {
				s=18+"";
			} else if (Integer.parseInt(s)== 6) {
				s=19+"";
			} else if (Integer.parseInt(s)== 8) {
				s=20+"";
			} else if (Integer.parseInt(s)== 10) {
				s=21+"";
			} else if (Integer.parseInt(s)== 12) {
				s=22+"";
			} else if (Integer.parseInt(s)== 14) {
				s=23+"";
			} else if (Integer.parseInt(s)== 16) {
				s=24+"";
			} else if (Integer.parseInt(s)== 18) {
				s=25+"";
			} else if (Integer.parseInt(s)== 20) {
				s=26+"";
			} else if (Integer.parseInt(s)== 22) {
				s=27+"";
			} else if (Integer.parseInt(s)== 24) {
				s=28+"";
			} else if (Integer.parseInt(s)== 26) {
				s=29+"";
			} else if (Integer.parseInt(s)== 28) {
				s=30+"";
			} else if (Integer.parseInt(s)== 30) {
				s=31+"";
			} else if (Integer.parseInt(s)== 32) {
				s=32+"";
			}
			
			/*if(s.length()==1) {
				s= "0"+s;
			}*/
		} catch (Exception e) {
			return Integer.parseInt(s)+"";
		}
		return Integer.parseInt(s)+"";		
	}

	public String toPlusSeqNo(String s) {
		try {
			
			if (s == null) {
				s= "0";
			} else if (Integer.parseInt(s)== 1) {
				s=1+"";
			} else if (Integer.parseInt(s)== 2) {
				s=3+"";
			} else if (Integer.parseInt(s)== 3) {
				s=5+"";
			} else if (Integer.parseInt(s)== 4) {
				s=7+"";
			} else if (Integer.parseInt(s)== 5) {
				s=9+"";
			} else if (Integer.parseInt(s)== 6) {
				s=11+"";
			} else if (Integer.parseInt(s)== 7) {
				s=13+"";
			} else if (Integer.parseInt(s)== 8) {
				s=15+"";
			} else if (Integer.parseInt(s)== 9) {
				s=17+"";
			} else if (Integer.parseInt(s)== 10) {
				s=19+"";
			} else if (Integer.parseInt(s)== 11) {
				s=21+"";
			} else if (Integer.parseInt(s)== 12) {
				s=23+"";
			} else if (Integer.parseInt(s)== 13) {
				s=25+"";
			} else if (Integer.parseInt(s)== 14) {
				s=27+"";
			} else if (Integer.parseInt(s)== 15) {
				s=29+"";
			} else if (Integer.parseInt(s)== 16) {
				s=31+"";
			} else if (Integer.parseInt(s)== 17) {
				s=2+"";
			} else if (Integer.parseInt(s)== 18) {
				s=4+"";
			} else if (Integer.parseInt(s)== 19) {
				s=6+"";
			} else if (Integer.parseInt(s)== 20) {
				s=8+"";
			} else if (Integer.parseInt(s)== 21) {
				s=10+"";
			} else if (Integer.parseInt(s)== 22) {
				s=12+"";
			} else if (Integer.parseInt(s)== 23) {
				s=14+"";
			} else if (Integer.parseInt(s)== 24) {
				s=16+"";
			} else if (Integer.parseInt(s)== 25) {
				s=18+"";
			} else if (Integer.parseInt(s)== 26) {
				s=20+"";
			} else if (Integer.parseInt(s)== 27) {
				s=22+"";
			} else if (Integer.parseInt(s)== 28) {
				s=24+"";
			} else if (Integer.parseInt(s)== 29) {
				s=26+"";
			} else if (Integer.parseInt(s)== 30) {
				s=28+"";
			} else if (Integer.parseInt(s)== 31) {
				s=30+"";
			} else if (Integer.parseInt(s)== 32) {
				s=32+"";
			}
			
			if(s.length()==1) {
				s= "0"+s;
			}
		} catch (Exception e) {
			return s;
		}
		return s;		
	}
}
