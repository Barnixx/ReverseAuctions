<%--
  Created by IntelliJ IDEA.
  User: Barni
  Date: 10.08.2018
  Time: 20:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage subcategoriesMap="${subcategoriesMap}">
	<!--Slider -->
	<div class="container-fluid" style="padding-left: 0px; padding-right: 0px;">
		<!-- #region Jssor Slider Begin -->
		<!-- Generator: Jssor Slider Maker -->
		<!-- Source: https://www.jssor.com -->
		<script src="../././views/js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="../././views/js/jssor.slider-27.4.0.min.js" type="text/javascript"></script>
		<script type="text/javascript">
            jQuery(document).ready(function ($) {

                var jssor_1_options = {
                    $AutoPlay: 1,
                    $ArrowNavigatorOptions: {
                        $Class: $JssorArrowNavigator$
                    },
                    $BulletNavigatorOptions: {
                        $Class: $JssorBulletNavigator$
                    }
                };

                var jssor_1_slider = new $JssorSlider$("jssor_1", jssor_1_options);

                /*#region responsive code begin*/

                var MAX_WIDTH = 4000;

                function ScaleSlider() {
                    var containerElement = jssor_1_slider.$Elmt.parentNode;
                    var containerWidth = containerElement.clientWidth;

                    if (containerWidth) {

                        var expectedWidth = Math.min(MAX_WIDTH || containerWidth, containerWidth);

                        jssor_1_slider.$ScaleWidth(expectedWidth);
                    }
                    else {
                        window.setTimeout(ScaleSlider, 30);
                    }
                }

                ScaleSlider();

                $(window).bind("load", ScaleSlider);
                $(window).bind("resize", ScaleSlider);
                $(window).bind("orientationchange", ScaleSlider);
                /*#endregion responsive code end*/
            });
		</script>
		<style>
			/*jssor slider loading skin oval css*/
			.jssorl-003-oval img {
				animation-name: jssorl-003-oval;
				animation-duration: 1.2s;
				animation-iteration-count: infinite;
				animation-timing-function: linear;
			}
			
			@keyframes jssorl-003-oval {
				from {
					transform: rotate(0deg);
				}
				to {
					transform: rotate(360deg);
				}
			}
			
			/*jssor slider bullet skin 031 css*/
			.jssorb031 {
				position: absolute;
			}
			
			.jssorb031 .i {
				position: absolute;
				cursor: pointer;
			}
			
			.jssorb031 .i .b {
				fill: #000;
				fill-opacity: 0.5;
				stroke: #fff;
				stroke-width: 1200;
				stroke-miterlimit: 10;
				stroke-opacity: 0.3;
			}
			
			.jssorb031 .i:hover .b {
				fill: #fff;
				fill-opacity: .7;
				stroke: #000;
				stroke-opacity: .5;
			}
			
			.jssorb031 .iav .b {
				fill: #fff;
				stroke: #000;
				fill-opacity: 1;
			}
			
			.jssorb031 .i.idn {
				opacity: .3;
			}
			
			/*jssor slider arrow skin 051 css*/
			.jssora051 {
				display: block;
				position: absolute;
				cursor: pointer;
			}
			
			.jssora051 .a {
				fill: none;
				stroke: #fff;
				stroke-width: 360;
				stroke-miterlimit: 10;
			}
			
			.jssora051:hover {
				opacity: .8;
			}
			
			.jssora051.jssora051dn {
				opacity: .5;
			}
			
			.jssora051.jssora051ds {
				opacity: .3;
				pointer-events: none;
			}
		</style>
		<div id="jssor_1" style="position:relative;margin:0
                auto;top:0px;left:0px;width:1280px;height:300px;overflow:hidden;visibility:hidden;">
			<!-- Loading Screen -->
			<div data-u="loading" class="jssorl-003-oval"
				 style="position:absolute;top:0px;left:0px;width:100%;height:100%;text-align:center;background-color:rgba(0,0,0,0.7);">
				<img style="margin-top:-19px;position:relative;top:50%;width:38px;height:38px;"
					 src="../././views/img/oval.svg"/>
			</div>
			<div data-u="slides"
				 style="cursor:default;position:relative;top:0px;left:0px;width:1280px;height:300px;overflow:hidden;">
				<div>
					<img data-u="image" src="../././views/img/image.png"/>
				</div>
				<div>
					<img data-u="image" src="../././views/img/slide.jpg"/>
				</div>
			</div>
			<!-- Bullet Navigator -->
			<div data-u="navigator" class="jssorb031" style="position:absolute;bottom:12px;right:12px;"
				 data-autocenter="1" data-scale="0.5" data-scale-bottom="0.75">
				<div data-u="prototype" class="i" style="width:16px;height:16px;">
					<svg viewbox="0 0 16000 16000" style="position:absolute;top:0;left:0;width:100%;height:100%;">
						<circle class="b" cx="8000" cy="8000" r="5800"></circle>
					</svg>
				</div>
			</div>
			<!-- Arrow Navigator -->
			<div data-u="arrowleft" class="jssora051" style="width:55px;height:55px;top:0px;left:25px;"
				 data-autocenter="2" data-scale="0.75" data-scale-left="0.75">
				<svg viewbox="0 0 16000 16000" style="position:absolute;top:0;left:0;width:100%;height:100%;">
					<polyline class="a" points="11040,1920 4960,8000 11040,14080 "></polyline>
				</svg>
			</div>
			<div data-u="arrowright" class="jssora051" style="width:55px;height:55px;top:0px;right:25px;"
				 data-autocenter="2" data-scale="0.75" data-scale-right="0.75">
				<svg viewbox="0 0 16000 16000" style="position:absolute;top:0;left:0;width:100%;height:100%;">
					<polyline class="a" points="4960,1920 11040,8000 4960,14080 "></polyline>
				</svg>
			</div>
		</div>
		<!-- #endregion Jssor Slider End -->
	</div>
	<!--Slider-->

</t:genericpage>
