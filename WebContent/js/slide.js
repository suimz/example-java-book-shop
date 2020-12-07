function initSlide(){
	var sWidth = $("#slide").width(); //��ȡ����ͼ�Ŀ�ȣ���ʾ�����
	var len = $("#slide ul li").length; //��ȡ����ͼ����
	var index = 0;
	var picTimer;

	//���´���������ְ�ť�Ͱ�ť��İ�͸������
	var btn = "<div class='btnBg'></div><div class='btn'>";
	for(var i=0; i < len; i++){
		btn += "<span>" + (i+1) + "</span>";
	}
	btn += "</div>"
	$("#slide").append(btn);
	$("#slide .btnBg").css("opacity",0.3);

	//Ϊ���ְ�ť�����껬���¼�������ʾ��Ӧ�����ݣ���վ��������
	$("#slide .btn span").mouseenter(function(){
		index = $("#slide .btn span").index(this);
		showPics(index);
	}).eq(0).trigger("mouseenter");

	//����Ϊ���ҹ�����������liԪ�ض�����ͬһ�����󸡶�������������Ҫ�������ΧulԪ�صĿ��
	$("#slide ul").css("width",sWidth * (len + 1));

	//��껬��ĳli�е�ĳdiv�������ͬ��divԪ�ص�͸���ȣ�����li�ı���Ϊ��ɫ�����Ի��б䰵��Ч��
	$("#slide ul li div").hover(function(){
		$(this).siblings().css("opacity",0.7);
	},function() {
		$("#slide ul li div").css("opacity",1);
	});

	//��껬�Ͻ���ͼʱֹͣ�Զ����ţ�����ʱ��ʼ�Զ�����
	$("#slide").hover(function(){
		clearInterval(picTimer);
	},function(){
		picTimer = setInterval(function(){
			if(index == len){ //�������ֵ����liԪ�ظ�����˵�����һ��ͼ������ϣ�������Ҫ��ʾ��һ��ͼ��������showFirPic()��Ȼ������ֵ����
				showFirPic();
				index = 0;
			}else{ //�������ֵ������liԪ�ظ���������ͨ״̬�л�������showPics()
				showPics(index);
			}
			index++;
		},3000); //��3000�����Զ����ŵļ������λ������
	}).trigger("mouseleave");
	
	//��ʾͼƬ���������ݽ��յ�indexֵ��ʾ��Ӧ������
	function showPics(index){  //��ͨ�л�
		var nowLeft = -index*sWidth; //����indexֵ����ulԪ�ص�leftֵ
		$("#slide ul").stop(true,false).animate({"left":nowLeft},500); //ͨ��animate()����ulԪ�ع������������position
		$("#slide .btn span").removeClass("on").eq(index).addClass("on"); //Ϊ��ǰ�İ�ť�л���ѡ�е�Ч��
	}

	function showFirPic(){ //���һ��ͼ�Զ��л�����һ��ͼʱר��
		$("#slide ul").append($("#slide ul li:first").clone());
		var nowLeft = -len*sWidth; //ͨ��liԪ�ظ�������ulԪ�ص�leftֵ��Ҳ�������һ��liԪ�ص��ұ�
		$("#slide ul").stop(true,false).animate({"left":nowLeft},500,function(){
			//ͨ��callback���ڶ����������ulԪ�����¶�λ����㣬Ȼ��ɾ�����һ�����ƹ�ȥ��Ԫ��
			$("#slide ul").css("left","0");
			$("#slide ul li:last").remove();
		}); 
		$("#slide .btn span").removeClass("on").eq(0).addClass("on"); //Ϊ��һ����ť���ѡ�е�Ч��
	}
}