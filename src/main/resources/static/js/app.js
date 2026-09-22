document.addEventListener("DOMContentLoaded", () => {
    document.querySelectorAll(".progress-fill").forEach((el) => {
        const width = el.style.width;
        el.style.width = "0";
        requestAnimationFrame(() => {
            el.style.width = width;
        });
    });
});
