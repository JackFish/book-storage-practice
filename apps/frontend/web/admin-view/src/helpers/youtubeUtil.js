/**
 * Created by ksb on 16. 11. 19.
 */
export function youtubeId(url) {
    let tag = "";
    if(url)  {
        const regExp = /^.*((youtu.be\/)|(v\/)|(\/u\/\w\/)|(embed\/)|(watch\?))\??v?=?([^#\&\?]*).*/;
        const matchs = url.match(regExp);
        if (matchs) {
            tag += matchs[7];
        }
        return tag;
    }
}

export function checkYoutubeUrl(url){
    if(url && /^(?:https?:\/\/)?(?:m\.|www\.)?(?:youtu\.be\/|youtube\.com\/(?:embed\/|v\/|watch\?v=|watch\?.+&v=))((\w|-){11})(?:\S+)?$/.test(url)){
        return true;
    } else {
        return false;
    }
}

export function getYoutubeSrc(url){
    return "http://img.youtube.com/vi/" + youtubeId(url) + "/0.jpg";
}