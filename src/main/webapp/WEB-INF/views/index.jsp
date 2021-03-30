<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<!-- Bootstrap file -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
  
<%@include file="include/header.jsp"%>

<div class="row justify-content-center" style="margin: 0 auto;">
	<div class="col-12 col-lg-10">
		<div class="section-heading">
			<h1 style="color: green; font-weight: bold;">독일트리</h1>
			<p>Germany Tree</p>
		</div>
	</div>
</div>

<!-- ##### Blog Content Area Start ##### -->
<section class="blog-content-area section-padding-20">
	<div class="container">
		<div class="row">
			<!-- Featured Post Area -->
			<div class="col-2"></div>
			<div class="col-8">
				<div class="featured-post-area">
					<!-- <iframe id="player" type="text/html" width="100%" height="409" src="http://www.youtube.com/embed/fDsBbNW68OE" frameborder="0" webkitallowfullscreen="" mozallowfullscreen="" allowfullscreen=""></iframe>  -->
					<video src="resources/video/germanyTree.mp4" width="100%" height="409"></video>
				</div>
			</div>
			<div class="col-2"></div>
		</div>
	</div>
</section>
<!-- ##### Blog Content Area End ##### -->
<div align="right">
<a href="chat" data-toggle="modal" data-target="#theModal" 
	class="nav-link" data-remote="chat" style="font-size: 28px; color: forestgreen; font-weight: bold;">채팅하기</a>
	<!-- Modal -->
	<div class="modal fade" id="theModal" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content" style="width: 100%; text-align: center;">
				<div class="modal-header" style="background: green;"></div>
				<div class="modal-body">... remote content from "data-remote"
					loads here ...</div>
					
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal" style="background: forestgreen;">Close</button>
				</div>
			</div>
		</div>
	</div>
</div>
</div>
	<!-- Call chat.jsp Script -->
	<script>
		$('#theModal').on('show.bs.modal', function(e) {
			var button = $(e.relatedTarget);
			var modal = $(this);
			modal.find('.modal-body').load(button.data("remote"));
		});
	</script>
<%@include file="include/footer.jsp"%>

</body>
</html>