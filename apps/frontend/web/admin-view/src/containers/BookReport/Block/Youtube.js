/**
 * Created by ksb on 16. 11. 19.
 */
import React, {PropTypes, Component} from 'react';
import {FormControl, FormGroup, Image, HelpBlock} from 'react-bootstrap';
import {checkYoutubeUrl, getYoutubeSrc} from '../../../helpers/youtubeUtil'

export default class Youtube extends Component {
    state = {
        youtubeSrc: null
    }

    searchYoutube(e){
        if(checkYoutubeUrl(e.target.value)){
            this.setState({youtubeSrc: getYoutubeSrc(e.target.value)});
        } else {
            this.setState({youtubeSrc: null});
        }
    }

    render() {
        const {block: {caption, youtubeUrl}} = this.props;
        const {youtubeSrc} = this.state;

        return (
            <div>
                <FormGroup style={{margin:"0 0 15px 0"}}>
                    <FormControl type="text" placeholder="캡션" {...caption} />
                    {caption.touched && caption.error && <HelpBlock>{caption.error}</HelpBlock>}
                </FormGroup>
                <FormGroup style={{margin:"0 0 15px 0"}} onChange={this.searchYoutube.bind(this)}>
                    <FormControl type="text" placeholder="유튜브 URL" {...youtubeUrl} />
                    {youtubeUrl.touched && youtubeUrl.error && <HelpBlock>{youtubeUrl.error}</HelpBlock>}
                </FormGroup>
                {
                    youtubeSrc && <Image src={youtubeSrc} rounded />
                }
            </div>
        )
    }

}