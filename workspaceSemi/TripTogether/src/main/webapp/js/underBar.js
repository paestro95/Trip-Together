const target = document.querySelector(".target");
const links = document.querySelectorAll(".submenu");

for (let i = 0; i < links.length; i++) {
	links[i].addEventListener("mouseenter", mouseenterFunc);
	links[i].addEventListener("mouseleave", mouseLeaveFunc);
}

function mouseenterFunc() {
		// alert('a');
		// 마우스가 올라가면 작동하는 함수
		if (!this.parentNode.classList.contains("active")) {
			for (let i = 0; i < links.length; i++) {
				// 액티브를 바꿈
				if (links[i].parentNode.classList.contains("active")) {
					links[i].parentNode.classList.remove("active");
				}
			}
			this.parentNode.classList.add("active");

			const width = this.getBoundingClientRect().width;
			const height = this.getBoundingClientRect().height;
			const left = this.getBoundingClientRect().left + window.pageXOffset;
			const top = this.getBoundingClientRect().top + window.pageYOffset;

			// 언더바가 따라다닐 위치 계산
			target.style.width = `${width}px`;
			target.style.height = `${height}px`;
			target.style.left = `${left}px`;
			target.style.top = `${top}px`;
			target.style.borderColor = "#8FAADC";
		}
	}

	function mouseLeaveFunc() {
		// 마우스가 떠나면 작동하는 함수
		for (let i = 0; i < links.length; i++) {
			links[i].parentNode.classList.remove("active");
		}
		target.style.borderColor = "transparent";
	}


function resizeFunc() {
	// 브라우저 창의 크기가 바뀌어도 따라가는 함수
	const active = document.querySelector(".option li.active");

	if (active) {
		const left = active.getBoundingClientRect().left + window.pageXOffset;
		const top = active.getBoundingClientRect().top + window.pageYOffset;

		target.style.left = `${left}px`;
		target.style.top = `${top}px`;
	}
}
window.addEventListener("resize", resizeFunc);